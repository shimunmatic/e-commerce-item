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
}
