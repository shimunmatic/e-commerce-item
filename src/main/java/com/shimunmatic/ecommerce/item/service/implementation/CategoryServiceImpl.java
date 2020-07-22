package com.shimunmatic.ecommerce.item.service.implementation;

import com.shimunmatic.ecommerce.item.model.Category;
import com.shimunmatic.ecommerce.item.repository.CategoryRepository;
import com.shimunmatic.ecommerce.item.service.definition.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends AbstractService<Category, Long> implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
        this.categoryRepository = repository;
    }
}
