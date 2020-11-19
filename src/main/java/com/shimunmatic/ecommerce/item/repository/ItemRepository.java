package com.shimunmatic.ecommerce.item.repository;

import com.shimunmatic.ecommerce.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Override
    @Query("select i from Item i where i.deleted = null or i.deleted = false")
    List<Item> findAll();

    @Query("select i from Item i where i.categoryId = :categoryId and (i.deleted = null or i.deleted = false)")
    List<Item> findAllByCategoryId(Long categoryId);

    @Query("select i from Item i where (fts(i.description,:keyword) = true ) and (i.deleted = null or i.deleted = false)")
    List<Item> findByKeyword(String keyword);
}
