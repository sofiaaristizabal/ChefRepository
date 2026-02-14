package com.example.mimido.dish.dto;

import java.math.BigDecimal;

public record DishResponseDTO (
        Long id,
        String name,
        BigDecimal price,
        Long ChefId,
        String chefName
){
}
