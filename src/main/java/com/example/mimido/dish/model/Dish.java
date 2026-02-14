package com.example.mimido.dish.model;
import jakarta.persistence.*;
import com.example.mimido.chef.model.Chef;
import lombok.Data;

import java.math.BigDecimal;

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
}
