package com.example.mimido.Ingredient.dto;

import com.example.mimido.Ingredient.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record IngredientCreateDto (
        @NotBlank(message = "name is required")
        @Size(min=2, max=120, message="name must be between 2 and 120 characters")
        String name,
        @NotBlank(message = "name is required")
        Category categoria

){
}
