package com.example.mimido.Ingredient.mapper;

import com.example.mimido.Ingredient.dto.IngredientResponseDto;
import com.example.mimido.Ingredient.dto.IngredientUpdateDto;
import com.example.mimido.Ingredient.model.Ingredient;

public class IngredientMapper {

    //primero tenemos que poner el singleton para que se instancie una sola vez
    private IngredientMapper(){}

    public static IngredientResponseDto toIngredientResponseDto(Ingredient ingredient){
        return new IngredientResponseDto(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getCategoria()
        );
    }

    public static Ingredient toEntity(IngredientResponseDto dto){
        Ingredient ingredient = new Ingredient();
        ingredient.setName(dto.name());
        ingredient.setCategoria(dto.categoria());
        return ingredient;
    }

    public static void updateEntity(Ingredient ingredient, IngredientUpdateDto dto){
        ingredient.setName(dto.name());
        ingredient.setCategoria(dto.categoria());
    }
}
