package com.shimunmatic.ecommerce.item.service.implementation;

import com.shimunmatic.ecommerce.item.converter.Converter;
import com.shimunmatic.ecommerce.item.dto.CategoryDTO;
import com.shimunmatic.ecommerce.item.model.Category;
import com.shimunmatic.ecommerce.item.repository.CategoryRepository;
import com.shimunmatic.ecommerce.item.service.definition.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends AbstractService<Category, Long> implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final Converter<Category, CategoryDTO> converter;

    public CategoryServiceImpl(CategoryRepository repository, Converter<Category, CategoryDTO> converter) {
        super(repository);
        this.categoryRepository = repository;
        this.converter = converter;
    }

    @Override
    public List<CategoryDTO> getAllDto() {
        return converter.toDto(getAll());
    }

    @Override
    public CategoryDTO saveDTO(CategoryDTO dto) {
        return converter.toDto(save(converter.toModel(dto)));
    }
}
