package com.example.mimido.dish.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record DishUpdateDTO(
        @NotBlank(message = "Fill this field")
        @Size(min=2, max = 140)
        String name,

        @NotBlank(message = "Fill this field")
        @DecimalMin(value="0.00", inclusive = false, message = "price must be grater than 0.00")
        @Digits(integer =8, fraction = 2)
        BigDecimal price,

        @NotNull(message = "ChefId is requires")
        @Positive(message = "ChefId has to ve positive ")
        Long ChefId
) { }
