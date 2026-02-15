package com.example.mimido.Ingredient.model;

import com.example.mimido.dish.model.Dish;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity()
@Table(name="ingredients")
@Data
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=50)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category categoria;

    //CORRECT OR ASK OR WHATEVER
    @ManyToMany(mappedBy = "ingredients") //mappedBy le dice que a relación esta siendo manejada por la otra entidad, o sea que dish es el dueño de esta relacion
    private Set<Dish> dishes = new HashSet<>();

}
