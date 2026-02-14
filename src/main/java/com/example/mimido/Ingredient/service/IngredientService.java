package com.example.mimido.Ingredient.service;

import com.example.mimido.Ingredient.repository.IngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    //constructor
    public IngredientService(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }


}
