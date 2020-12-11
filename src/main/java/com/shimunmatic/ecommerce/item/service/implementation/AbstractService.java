package com.shimunmatic.ecommerce.item.service.implementation;

import com.shimunmatic.ecommerce.item.service.definition.CRUDService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class AbstractService<T, U> implements CRUDService<T, U> {
    private final JpaRepository<T, U> repository;

    public AbstractService(JpaRepository<T, U> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<T> getById(U id) {
        return repository.findById(id);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(T entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(U id) {
        repository.deleteById(id);
    }
}
