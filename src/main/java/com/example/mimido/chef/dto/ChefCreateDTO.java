package com.example.mimido.chef.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChefCreateDTO (
        @NotBlank(message = "name is requires")
        @Size(min=2, max=120, message="name must be between 2 and 120 characters")
        String name,
        @Size( max=80, message="specialty must be 80 characters")
        String specialty,
        @Size(max=100)
        String description

){
}