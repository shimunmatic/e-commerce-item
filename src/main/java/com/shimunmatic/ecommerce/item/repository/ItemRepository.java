package com.shimunmatic.ecommerce.item.repository;

import com.shimunmatic.ecommerce.item.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Override
    @Query("select i from Item i where i.deleted = null or i.deleted = false")
    List<Item> findAll();

    @Override
    @Query("select i from Item i where i.deleted = null or i.deleted = false")
    Page<Item> findAll(Pageable pageable);

    @Query("select i from Item i where i.categoryId = :categoryId and (i.deleted = null or i.deleted = false)")
    List<Item> findAllByCategoryId(Long categoryId);

    @Query("select i from Item i where i.categoryId = :categoryId and (i.deleted = null or i.deleted = false)")
    Page<Item> findAllByCategoryId(Long categoryId, Pageable pageable);

    @Query("select i from Item i where (fts(i.description,:keyword) = true ) and (i.deleted = null or i.deleted = false)")
    List<Item> findByKeyword(String keyword);

    @Transactional
    @Modifying
    @Query("update Item i set i.thumbnail = :thumbnail where i.id = :itemId")
    void updateThumbnail(Long itemId, String thumbnail);
}
