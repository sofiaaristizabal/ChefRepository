package com.example.mimido.Ingredient.controller;

import com.example.mimido.Ingredient.dto.IngredientCreateDto;
import com.example.mimido.Ingredient.dto.IngredientResponseDto;
import com.example.mimido.Ingredient.dto.IngredientUpdateDto;
import com.example.mimido.Ingredient.service.IngredientService;
import com.example.mimido.dish.dto.DishResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ingredient")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService){
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientResponseDto postEntity(@Valid @RequestBody IngredientCreateDto dto){
        return ingredientService.create(dto);
    }

    @GetMapping
    public List<IngredientResponseDto> findAll(){
        return ingredientService.findAll();
    }

    @GetMapping("/{id}")
    public IngredientResponseDto findById(@PathVariable Long id){
        return ingredientService.findById(id);
    }

    @PutMapping("/{id}")
    public IngredientResponseDto updateEntity(@PathVariable Long id, @Valid @RequestBody IngredientUpdateDto dto){
        return ingredientService.updateById(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntity(@PathVariable Long id){
        ingredientService.deleteById(id);
    }

    @GetMapping("/{id}/dishes")
    public List<DishResponseDTO> getDishesByIngredient(@PathVariable Long id){
        return ingredientService.getDishesByIngredient(id);
    }
}
