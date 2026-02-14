package com.example.mimido.Ingredient.model;

import com.example.mimido.dish.model.Dish;
import jakarta.persistence.*;
import lombok.Data;

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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="dish_id", nullable = false)
    private Dish dish;

}
