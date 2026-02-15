package com.example.mimido.dish.controller;

import com.example.mimido.Ingredient.dto.IngredientResponseDto;
import com.example.mimido.dish.dto.DishCreateDTO;
import com.example.mimido.dish.dto.DishResponseDTO;
import com.example.mimido.dish.dto.DishUpdateDTO;
import com.example.mimido.dish.service.DishService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dishes")
//consultar como paginar
public class DishController {
    private DishService dishService;

    public DishController (DishService dishService){
        this.dishService = dishService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DishResponseDTO postEntity(@Valid @RequestBody DishCreateDTO dish){
        return dishService.create(dish);
    }

    @GetMapping
    public List<DishResponseDTO> findAll(){
        return dishService.findAll();
    }

    //pagination
    @GetMapping(params = {"page", "size"})
    public Page<DishResponseDTO> findAllPages(Pageable pageable){
        return dishService.findAllPages(pageable);
    }

    @GetMapping("/{id}")
    public DishResponseDTO findById(@PathVariable Long id){
        return dishService.findById(id);
    }

    @GetMapping("/by-chef/{chefId}")
    public List<DishResponseDTO> findByChef(@PathVariable Long chefId){
        return dishService.findByChef(chefId);
    }

    @PutMapping("/{id}")
    public DishResponseDTO updateEntity(@PathVariable Long id, @Valid @RequestBody DishUpdateDTO dish){
        return dishService.updateById(id, dish);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntity(@PathVariable Long id){
        dishService.deleteById(id);
    }

    @PostMapping("/{dishId}/ingredients/{ingredientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addIngredientToDish(@PathVariable Long dishId, @PathVariable Long ingredientId){
        dishService.addIngredientToDishById(dishId, ingredientId);
    }

    @GetMapping("/{id}/ingredients")
    public List<IngredientResponseDto> getIngredientsByDish(@PathVariable Long id){
        return dishService.getIngredientsByDih(id);
    }

}
