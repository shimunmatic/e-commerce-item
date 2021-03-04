package com.shimunmatic.ecommerce.item.repository;

import com.shimunmatic.ecommerce.item.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    @Query("select c from Category c where c.parentId = null and (c.deleted = null or c.deleted = false) order by c.name")
    List<Category> findAll();

    @Query("select c from Category c where c.deleted = null or c.deleted = false order by c.name")
    List<Category> findAllFlat();

    @Transactional
    @Modifying
    @Query("update Category c set c.thumbnail = :thumbnail where c.id = :categoryId")
    void updateThumbnail(Long categoryId, String thumbnail);
}
