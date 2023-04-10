package com.zadaca.events.services;

import com.zadaca.events.domains.Category;
import com.zadaca.events.payload.request.CategoryRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    Category getCategory(Long id);
    ResponseEntity<?> addCategory(CategoryRequest categoryRequest);
    ResponseEntity<?> editCategory(Long id, CategoryRequest categoryRequest);
}
