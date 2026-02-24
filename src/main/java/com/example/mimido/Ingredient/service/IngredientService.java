package com.example.mimido.Ingredient.service;

import com.example.mimido.Ingredient.dto.IngredientCreateDto;
import com.example.mimido.Ingredient.dto.IngredientResponseDto;
import com.example.mimido.Ingredient.dto.IngredientUpdateDto;
import com.example.mimido.Ingredient.mapper.IngredientMapper;
import com.example.mimido.Ingredient.model.Ingredient;
import com.example.mimido.Ingredient.repository.IngredientRepository;
import com.example.mimido.common.exception.NotFound;
import com.example.mimido.dish.dto.DishResponseDTO;
import com.example.mimido.dish.mapper.DishMapper;
import com.example.mimido.dish.repository.DishRepository;
import com.example.mimido.dish.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    //constructor
    public IngredientService(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    public IngredientResponseDto create(IngredientCreateDto dto){
        Ingredient ingredient = IngredientMapper.toEntity(dto);
        ingredientRepository.save(ingredient);
        return IngredientMapper.toIngredientResponseDto(ingredient);
    }

    public IngredientResponseDto findById(Long id){
        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow(()-> new NotFound("Ingredient not found"));
        return IngredientMapper.toIngredientResponseDto(ingredient);
    }

    public Ingredient getEntityById(Long id){
        return ingredientRepository.findById(id).orElseThrow(()-> new NotFound("Ingredient not found"));
    }
    //something, differenyyt

    public List<IngredientResponseDto> findAll(){
        List<Ingredient> ingredients = ingredientRepository.findAll();
        return ingredients.stream().map(IngredientMapper::toIngredientResponseDto).toList();
    }

    public IngredientResponseDto updateById(Long id, IngredientUpdateDto dto){
        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow(()-> new NotFound("Ingrediente no encontrado"));
        ingredient.setName(dto.name());
        ingredient.setCategoria(dto.categoria());
        Ingredient saved = ingredientRepository.save(ingredient);
        return IngredientMapper.toIngredientResponseDto(saved);
    }

    public void deleteById(Long id){
        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow(()-> new NotFound("Ingrediente no encontrado"));
        ingredientRepository.delete(ingredient);
    }

    public List<DishResponseDTO> getDishesByIngredient(Long id){
        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow(()-> new NotFound("Ingrediente no encontrado"));
        return ingredient.getDishes().stream().map(DishMapper::toResponseDTO).toList();
    }


}
