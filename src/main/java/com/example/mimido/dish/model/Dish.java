package com.example.mimido.dish.model;
import com.example.mimido.Ingredient.model.Ingredient;
import jakarta.persistence.*;
import com.example.mimido.chef.model.Chef;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="dishes")
@Data
public class Dish{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, precision = 10, scale=2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name ="chef_id", nullable = false)
    private Chef chef;

    @ManyToMany
    @JoinTable(
            name = "dish_ingredients", //JPA automaticamente crea una tabla intermedia con el nombre dish_ingredients y le decimos las columnas con las foreign_key
            inverseJoinColumns = @JoinColumn(name = "dish_id"),
            joinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients = new HashSet<>();

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
        ingredient.getDishes().add(this); //añadimos a la lista de platos de este ingrediente, este plato
    }

    public void removeIngredient(Ingredient ingredient){
        //if (ingredient in )
        this.ingredients.remove(ingredient);
        ingredient.getDishes().remove(this);
    }
}
