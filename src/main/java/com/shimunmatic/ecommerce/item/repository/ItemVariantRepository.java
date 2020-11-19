package com.shimunmatic.ecommerce.item.repository;

import com.shimunmatic.ecommerce.item.model.ItemVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemVariantRepository extends JpaRepository<ItemVariant, Long> {

    @Override
    @Query("select i from ItemVariant i where i.deleted = null or i.deleted = false")
    List<ItemVariant> findAll();

    @Query("select iv from ItemVariant iv where iv.itemId = :itemId and (iv.deleted = null or iv.deleted = false)")
    List<ItemVariant> findAllByItemId(Long itemId);
}
