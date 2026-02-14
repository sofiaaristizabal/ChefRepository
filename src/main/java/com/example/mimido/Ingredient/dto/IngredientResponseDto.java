package com.example.mimido.Ingredient.dto;

import com.example.mimido.Ingredient.model.Category;

public record IngredientResponseDto (
        Long id,
        String name,
        Category categoria
){
}
