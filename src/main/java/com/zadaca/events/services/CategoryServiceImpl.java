package com.zadaca.events.services;

import com.zadaca.events.domains.Category;
import com.zadaca.events.domains.Location;
import com.zadaca.events.payload.request.CategoryRequest;
import com.zadaca.events.payload.response.MessageResponse;
import com.zadaca.events.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) {
        return this.categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public ResponseEntity<?> addCategory(CategoryRequest categoryRequest) {
        Category newCategory = new Category();
        newCategory.setName(categoryRequest.getName());
        newCategory.setIconUrl(categoryRequest.getIconUrl());

        if(newCategory.getName() == null || newCategory.getIconUrl() == null){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: All fields must be entered!"));
        }

        this.categoryRepository.save(newCategory);
        return ResponseEntity.ok()
                .body(new MessageResponse("Category saved successfully!"));
    }

    @Override
    public ResponseEntity<?> editCategory(Long id, CategoryRequest categoryRequest) {
        if (!this.categoryRepository.existsById(id)){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Location with entered ID does not exist!"));
        }

        Category newCategory = this.categoryRepository.findById(id).orElseThrow();

        if (categoryRequest.getName() != null){
            newCategory.setName(categoryRequest.getName());
        }
        if (categoryRequest.getName() != null){
            newCategory.setName(categoryRequest.getName());
        }
        if (categoryRequest.getIconUrl() != null){
            newCategory.setIconUrl(categoryRequest.getIconUrl());
        }

        this.categoryRepository.save(newCategory);
        return ResponseEntity.ok()
                .body(new MessageResponse("Category saved successfully!"));
    }
}
