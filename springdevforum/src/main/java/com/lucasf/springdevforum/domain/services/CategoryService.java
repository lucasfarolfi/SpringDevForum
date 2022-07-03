package com.lucasf.springdevforum.domain.services;

import com.lucasf.springdevforum.domain.entities.Category;
import com.lucasf.springdevforum.application.exceptions.ObjectNotFoundException;
import com.lucasf.springdevforum.infraestructure.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Page<Category> findAll(Pageable pageable){
        return categoryRepository.findAll(pageable);
    }

    public Category findById(String id){
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElseThrow(() -> new ObjectNotFoundException("Category not found."));
    }

}
