package com.example.mimido.dish.service;

import com.example.mimido.chef.dto.ChefResponseDTO;
import com.example.mimido.chef.mapper.ChefMapper;
import com.example.mimido.chef.model.Chef;
import com.example.mimido.chef.service.ChefService;
import com.example.mimido.common.exception.NotFound;
import com.example.mimido.dish.dto.DishCreateDTO;
import com.example.mimido.dish.dto.DishResponseDTO;
import com.example.mimido.dish.dto.DishUpdateDTO;
import com.example.mimido.dish.mapper.DishMapper;
import com.example.mimido.dish.model.Dish;
import com.example.mimido.dish.repository.DishRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final ChefService chefService;

    public DishService(DishRepository dishRepository, ChefService chefService){
        this.dishRepository = dishRepository;
        this.chefService = chefService;
    }

    public DishResponseDTO create(DishCreateDTO dto){
        Chef chef = chefService.getEntityById(dto.ChefId());
        Dish dish = DishMapper.toEntity(dto, chef);
        dishRepository.save(dish);
        return DishMapper.toResponseDTO(dish);
    }

    public DishResponseDTO findById(Long id){
        Dish dish = dishRepository.findById(id).orElseThrow(()->new NotFound("Not Found"));
        return DishMapper.toResponseDTO(dish);
    }

    public List<DishResponseDTO> findAll(){
        List<Dish> dishes = dishRepository.findAll();
        return dishes.stream().map(DishMapper::toResponseDTO).toList();
    }

    //Pagination
    //pageable represents what the user request (the offset and limit), it must have pageNumber, pageSize (items per page)  and sort (asendiente o decendiente y se puede repetir), these from come the Http query parameters ejemplo: GET /api/dishes?page=1&size=5&sort=price,desc, la primera pagina es la numero 0
    // Page representa el batch de data y la metadata de todo el dataset Page<Dish> = {
    //  content: [ dish1, dish2, dish3 ],
    //  pageNumber: 1,
    //  pageSize: 3,
    //  totalElements: 42,
    //  totalPages: 14,
    //  first: false,
    //  last: false
    //}
    public Page<DishResponseDTO> findAllPages(Pageable pageable){
       return dishRepository.findAll(pageable).map(DishMapper::toResponseDTO);
    }

    public List<DishResponseDTO> findByChef(Long chefId){
        //Validar que el chef exista
        List<Dish> dishes = dishRepository.findByChefId(chefId);
        return dishes.stream().map(DishMapper::toResponseDTO).toList();
    }

    public DishResponseDTO updateById(Long id, DishUpdateDTO dto){
        Dish dish = dishRepository.findById(id).orElseThrow(()->new NotFound(("chef no encontrado")));
        Chef chef = chefService.getEntityById(dto.ChefId());
        dish.setChef(chef);
        dish.setName(dto.name());
        dish.setPrice(dto.price());
        Dish saved = dishRepository.save(dish);
        return DishMapper.toResponseDTO(saved);
    }

    public void deleteById(Long id){
        Dish dish = dishRepository.findById(id).orElseThrow(()->new NotFound(("dish not found ")));
        dishRepository.delete(dish);
    }
}
