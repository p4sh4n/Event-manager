package com.zadaca.events.controllers;

import com.zadaca.events.domains.Category;
import com.zadaca.events.domains.Location;
import com.zadaca.events.payload.request.CategoryRequest;
import com.zadaca.events.payload.request.LocationRequest;
import com.zadaca.events.services.CategoryService;
import com.zadaca.events.services.LocationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Categories")
@RequestMapping("/api/v1")
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getLocations(){
        return this.categoryService.getCategories();
    }
    @GetMapping("/categories/{id}")
    public Category getLocation(@PathVariable("id") Long id){
        return this.categoryService.getCategory(id);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<?> addLocation(@RequestBody CategoryRequest categoryRequest){
        return this.categoryService.addCategory(categoryRequest);
    }
    @PutMapping("/admin/categories/{id}")
    public ResponseEntity<?> editLocation(@PathVariable("id") Long id, @RequestBody CategoryRequest categoryRequest){
        return this.categoryService.editCategory(id, categoryRequest);
    }
}
