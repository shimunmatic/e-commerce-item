package com.shimunmatic.ecommerce.item.controller;

import com.shimunmatic.ecommerce.item.converter.Converter;
import com.shimunmatic.ecommerce.item.dto.CategoryCriteria;
import com.shimunmatic.ecommerce.item.dto.CategoryDTO;
import com.shimunmatic.ecommerce.item.model.Category;
import com.shimunmatic.ecommerce.item.response.ResponseObject;
import com.shimunmatic.ecommerce.item.service.definition.CategoryService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/item/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final Converter<Category, CategoryDTO> converter;

    @Autowired
    public CategoryController(CategoryService categoryService, Converter<Category, CategoryDTO> converter) {
        this.categoryService = categoryService;
        this.converter = converter;
    }

    @GetMapping
    public ResponseEntity<ResponseObject<List<CategoryDTO>>> getCategories(CategoryCriteria criteria) {
        log.info("...getCategories...");

        List<CategoryDTO> dtos = categoryService.getAllDto(criteria);

        return ResponseEntity.ok(ResponseObject.ofData(dtos));
    }

    @PostMapping
    @ApiResponse()
    public ResponseEntity<ResponseObject<CategoryDTO>> saveNewCategory(@RequestBody CategoryDTO categoryDTO) {
        log.info("...saveNewCategory...{}", categoryDTO);

        CategoryDTO savedDTO = converter.toDto(categoryService.save(converter.toModel(categoryDTO)));

        return ResponseEntity.ok(ResponseObject.ofData(savedDTO));
    }

    @PutMapping(path = "{categoryId}")
    public ResponseEntity<ResponseObject<CategoryDTO>> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryDTO categoryDTO) {
        log.info("...updateCategory...{}", categoryDTO);

        CategoryDTO savedDTO = categoryService.update(categoryId, categoryDTO);

        return ResponseEntity.ok(ResponseObject.ofData(savedDTO));
    }

    @PostMapping(path = "{categoryId}/thumbnail")
    public ResponseEntity<?> uploadThumbnail(@PathVariable("categoryId") Long categoryId, @RequestParam("file") MultipartFile file) throws IOException {
        log.info("...uploadThumbnail...{}", categoryId);
        categoryService.uploadThumbnail(categoryId, file);
        return ResponseEntity.ok().build();
    }
}
