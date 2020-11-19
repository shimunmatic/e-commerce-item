package com.shimunmatic.ecommerce.item.repository;

import com.shimunmatic.ecommerce.item.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Override
    @Query("select d from Discount d where d.deleted = null or d.deleted = false")
    List<Discount> findAll();
}
