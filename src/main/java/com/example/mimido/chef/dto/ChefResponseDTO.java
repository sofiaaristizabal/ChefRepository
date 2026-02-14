package com.example.mimido.chef.dto;

import java.util.List;

public record ChefResponseDTO(
        Long id,
        String specialty,
        String description,
        String name,
        List<Long> dishIds
) {
}
