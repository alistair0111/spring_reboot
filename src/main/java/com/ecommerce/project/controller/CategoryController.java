package com.ecommerce.project.controller;


import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/echo")
    public ResponseEntity<String> echoMessage(@RequestParam(name="message") String message) {
        return new ResponseEntity<>("Echoed message: "+message, HttpStatus.OK);
    }


    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name="pageNumber", defaultValue = AppConstants.PAGE_NUMBER)Integer pageNumber,
            @RequestParam(name="pageSize", defaultValue = AppConstants.PAGE_SIZE)Integer pageSize,
            @RequestParam(name="sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR) String sortOrder
    ) {
        return new ResponseEntity<>(categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder), HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>( savedCategoryDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable long categoryId) {
        return new ResponseEntity<>( categoryService.deleteCategory(categoryId), HttpStatus.OK);
    }

    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO,
                                                 @PathVariable long categoryId) {
        return new ResponseEntity<>(categoryService.updateCategory(categoryDTO, categoryId), HttpStatus.OK);

    }
}                                                                                   
