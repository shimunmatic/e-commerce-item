package com.shimunmatic.ecommerce.item.repository;

import com.shimunmatic.ecommerce.item.model.ItemMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMediaRepository extends JpaRepository<ItemMedia, Long> {
}
