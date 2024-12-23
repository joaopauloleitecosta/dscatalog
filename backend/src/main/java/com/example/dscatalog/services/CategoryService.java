package com.example.dscatalog.services;

import com.example.dscatalog.dto.CategoryDTO;
import com.example.dscatalog.entities.Category;
import com.example.dscatalog.repositories.CategoryRepository;
import com.example.dscatalog.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAll();
        return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());

        //List<CategoryDTO> listDTO = new ArrayList<>();
        //for(Category cat: list) {
        //    listDTO.add(new CategoryDTO(cat));
        //}

    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> opt = repository.findById(id);
        Category entity = opt.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return new CategoryDTO(entity);
    }

}
