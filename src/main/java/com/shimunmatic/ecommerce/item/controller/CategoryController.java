package com.shimunmatic.ecommerce.item.controller;

import com.shimunmatic.ecommerce.item.converter.Converter;
import com.shimunmatic.ecommerce.item.dto.CategoryDTO;
import com.shimunmatic.ecommerce.item.model.Category;
import com.shimunmatic.ecommerce.item.service.definition.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/item/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final Converter<Category, CategoryDTO> converter;

    @Autowired
    public CategoryController(CategoryService categoryService, Converter<Category, CategoryDTO> converter) {
        this.categoryService = categoryService;
        this.converter = converter;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        log.info("...getCategories...");
        List<Category> models = categoryService.getAll();
        List<CategoryDTO> dtos = converter.toDto(models);

        return ResponseEntity.ok(dtos);
    }
}
