package com.example.mimido.dish.repository;

import com.example.mimido.dish.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {

    List<Dish> findByChefId(Long chefId); //@Query ("Select * from ...")


}
