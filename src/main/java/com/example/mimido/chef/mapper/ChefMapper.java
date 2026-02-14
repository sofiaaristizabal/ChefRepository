package com.example.mimido.chef.mapper;

import com.example.mimido.chef.dto.ChefCreateDTO;
import com.example.mimido.chef.dto.ChefResponseDTO;
import com.example.mimido.chef.dto.ChefUpdateDTO;
import com.example.mimido.chef.model.Chef;
import com.example.mimido.dish.model.Dish;

import java.util.ArrayList;
import java.util.List;

public class ChefMapper {

    private ChefMapper(){}
    public static ChefResponseDTO toChefResponseDTO(Chef chef){
        List<Long> dishesIds;

        if(chef.getDishes() == null){
            dishesIds = new ArrayList<>();
        }else{
            dishesIds = chef.getDishes().stream().map(Dish::getId).toList(); //el map es como un for pero mucho mas legible
        }

        return new ChefResponseDTO(
                chef.getId(),
                chef.getSpecialty(),
                chef.getName(),
                chef.getDescription(),
                dishesIds
        );
    }

    public static Chef toEntity(ChefCreateDTO dto){
        Chef chef = new Chef();
        chef.setName(dto.name());
        chef.setDescription(dto.description());
        chef.setSpecialty(dto.specialty());
        return chef;
    }

    public static void updateEntity(Chef chef, ChefUpdateDTO dto){
        chef.setName(dto.name());
        chef.setDescription(dto.description());
        chef.setSpecialty(dto.specialty());
    }
}
