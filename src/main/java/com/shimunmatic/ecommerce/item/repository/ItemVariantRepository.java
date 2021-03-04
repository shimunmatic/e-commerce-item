package com.shimunmatic.ecommerce.item.repository;

import com.shimunmatic.ecommerce.item.model.ItemVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemVariantRepository extends JpaRepository<ItemVariant, Long> {

    @Override
    @Query("select i from ItemVariant i where i.deleted = null or i.deleted = false")
    List<ItemVariant> findAll();

    @Query("select iv from ItemVariant iv where iv.itemId = :itemId and (iv.deleted = null or iv.deleted = false)")
    List<ItemVariant> findAllByItemId(Long itemId);

    @Query("select iv from ItemVariant iv where iv.id = :variantId and iv.itemId = :itemId and (iv.deleted = null or iv.deleted = false)")
    Optional<ItemVariant> findOneByItemIdAndId(Long itemId, Long variantId);

    @Transactional
    @Modifying
    @Query("update ItemVariant iv set iv.thumbnail = :thumbnail where iv.id = :itemVariantId")
    void updateThumbnail(Long itemVariantId, String thumbnail);
}
