package com.example.mimido.chef.model;

import com.example.mimido.dish.model.Dish;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
import java.util.List;

@Entity
@Table(name="chefs")
@Data //es de lombok, crea los getters y setters
public class Chef{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length = 120)
    private String name;

    @Column(nullable = false, length = 120)
    private String description;

    @Column(nullable = false)
    private String specialty;

    @OneToMany(mappedBy = "chef", cascade = CascadeType.ALL)
    private List<Dish> dishes;

}
