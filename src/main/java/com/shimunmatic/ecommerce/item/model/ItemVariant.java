package com.shimunmatic.ecommerce.item.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "item_variant", schema = "sc_ecommerce_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Builder
public class ItemVariant extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "item_id")
    private Long itemId;
    @Column(name = "base_price")
    private Double basePrice;
}
