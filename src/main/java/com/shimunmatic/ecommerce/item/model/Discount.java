package com.shimunmatic.ecommerce.item.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "discount", schema = "sc_ecommerce_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Builder
public class Discount extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "value")
    private Double value;
    @Column(name = "value_type")
    private DiscountValueType discountValueType;
    @Column(name = "date_valid_from")
    private Instant dateValidFrom;
    @Column(name = "date_valid_to")
    private Instant dateValidTo;
}
