package com.example.mimido.dish.mapper;

import com.example.mimido.chef.model.Chef;
import com.example.mimido.dish.dto.DishCreateDTO;
import com.example.mimido.dish.dto.DishResponseDTO;
import com.example.mimido.dish.dto.DishUpdateDTO;
import com.example.mimido.dish.model.Dish;

public class DishMapper {
    private DishMapper(){}

    public static Dish toEntity(DishCreateDTO dto, Chef chef){
        Dish dish = new Dish();
        dish.setName(dto.name());
        dish.setPrice(dto.price());
        dish.setChef(chef);
        return dish;
    }

    public static void updateEntity(Dish dish, DishUpdateDTO dto, Chef chef){
        dish.setName(dto.name());
        dish.setPrice(dto.price());
        dish.setChef(chef);
    }

    public static DishResponseDTO toResponseDTO (Dish dish) {
        Long chefId = null;
        String chefName = null;

        if(dish.getChef() != null){
            chefId = dish.getChef().getId();
            chefName = dish.getChef().getName();
        }

        return new DishResponseDTO(
                dish.getId(),
                dish.getName(),
                dish.getPrice(),
                chefId,
                chefName
        );
    }
}
