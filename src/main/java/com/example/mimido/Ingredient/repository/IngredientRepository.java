package com.example.mimido.Ingredient.repository;

import com.example.mimido.Ingredient.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
