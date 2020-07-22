package com.shimunmatic.ecommerce.item.repository;

import com.shimunmatic.ecommerce.item.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Override
    @Query("select c from Category c where c.deleted = null or c.deleted = false")
    List<Category> findAll();
}
