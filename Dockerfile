#--Build sateg--#
FROM eclipse-temurin:25-jdk AS build

WORKDIR /app

#copiamos wrapper y pom primero para cachear depedencias. Basicamente cuando creamos un contenedor es como una instancia de Linux vacia, por ende primero hay que instalar las dependencias de maven para poder ejecutar spring boot
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
#tenemos el maven wrapper, maven wrapper config y el pom que tiene las definiciones de dependencias. Basicamente copiamos cada una de estas y las corremos en el siguiente comando, el punto de esto es que docker funciona con layers, entonces separamos las dependencias el source code, entonces si hay algun cambio en el source code no es necesario correr TODO de nuevo si no que corremos solo el codigo otra vez porque en un layer ya habiamos puesto todas las dependencias. el COPY .mvn .mvn es que copio la carpeta .mvn en una carpeta llamada .mvn

RUN chmod +x mvnw && ./mvnw -DskipTests dependency:go-offline #como es un linux de cero hay que darle permisos de ejecución para el maven, y el go offline significa que descarga TODAS las dependencias, y el DSkiptest es para saltarse los test

#copiamos the whole code
COPY src ./src

RUN ./mvnw -DskipTests clean package #corro todo lo de la carpeta ./mvnw lo que compila la app y crea el .jar, dskip es para que no corra los test

#Basicamente el jdk sirve para desarrollo, cuando corremos el mvn clean package lo empaquetamos en un .jar que se puede ejecutar
# Usamos entonces el JDK y maven  para descargar las dependencias, compilar el codigo y crear el Jar

#-----Run stage-----#
#La primera imagen, el build stage es una imagen temporal, esta se mantiene internamene para permitir controlled copyng the stage A a Stage B, Ahora creamos una segunda imagen mas pequeña que es mas rapida para montarse y no necesita maven ni source code. Cuando se termine de contruir la imagen del stage B se elimina el stage A (o sea el build)
#Multi-stage build
FROM eclipse-temurin:25-jre
#Usamos jre porque no necesitamos compilar, solo correr Java bytecode
WORKDIR /app

RUN useradd -r -u 1001 runApp
#estamos creando un usuario llamado runApp ¿, el flag -r es para decir que este usuario solo sirve para correr servicios, no para loggearse, u el -u es para asignarle un ID, en este cso 1001 (normalmente root s 0, y de 1 a 999 se usa para usuarios reguklres)

COPY --from=build /app/target/*.jar app.jar
#Aqui estamos referenciando el build que habiamos creado antes. el from copia e build stage, /app/target/*.jar es  la aplicacion compilada y la renombramos como app.jar, no copia source code, maven, credenciales, etc.
#Basicamente estamos "From the filesystem snapshot of stage named build, copy this file into the current stage”

RUN chown runApp:runApp app.jar
#le estamos dando propiedad del archivo app.jar a el usuario runApp, del grupo runApp

USER runApp
#cambiamos de usuario

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
#el entrypoint crea un Java app container

#una vez creada la imagen del runtime no se podra volver a acceder al buildstage. Sirve para seguridad y tamaño, la idea es que el ambiente de producción no tenga maven ni el source code y asi pesa menos
#stage: temporary filesystem snapshot