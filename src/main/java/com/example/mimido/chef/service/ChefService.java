package com.example.mimido.chef.service;

import com.example.mimido.chef.dto.ChefCreateDTO;
import com.example.mimido.chef.dto.ChefResponseDTO;
import com.example.mimido.chef.dto.ChefUpdateDTO;
import com.example.mimido.chef.mapper.ChefMapper;
import com.example.mimido.chef.model.Chef;
import com.example.mimido.chef.repository.ChefRepository;
import com.example.mimido.common.exception.NotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefService {

    private final ChefRepository chefRepository; //spring boot ya deja instancear interfaces, este es el singleton
    public ChefService( ChefRepository chefRepository){
        this.chefRepository = chefRepository;
    }
    public ChefResponseDTO create(ChefCreateDTO dto){
        Chef chef = ChefMapper.toEntity(dto);
        Chef saved = chefRepository.save(chef);
        return ChefMapper.toChefResponseDTO(saved);
    }

    public List<ChefResponseDTO> findAll(){
        List<Chef> chefs = chefRepository.findAll();
        return chefs.stream().map(ChefMapper::toChefResponseDTO).toList();
    }

    public ChefResponseDTO findById(Long id){
        Chef chef = chefRepository.findById(id).orElseThrow(()->new NotFound("chef not found"));
        return ChefMapper.toChefResponseDTO(chef);
    }

    public void deleteById(Long id){
        Chef chef = chefRepository.findById(id).orElseThrow(()-> new NotFound("Chef not found 😒"));
        chefRepository.delete(chef);
    }

    public ChefResponseDTO update(Long id, ChefUpdateDTO dto){
        Chef chef  = chefRepository.findById(id).orElseThrow(()->new NotFound("Chef not Found "));
        ChefMapper.updateEntity(chef, dto);
        Chef saved = chefRepository.save(chef);
        return ChefMapper.toChefResponseDTO(saved);
    }

    public Chef getEntityById(Long Id){
        return chefRepository.findById(Id).orElseThrow(()->new NotFound("NotFound"));
    }
}
