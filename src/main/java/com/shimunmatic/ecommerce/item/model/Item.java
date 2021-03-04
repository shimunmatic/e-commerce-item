package com.shimunmatic.ecommerce.item.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "item", schema = "sc_ecommerce_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Builder
public class Item extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "base_price")
    private Double basePrice;
    @OneToMany
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private List<ItemVariant> variants;
    @OneToMany
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private List<ItemMedia> itemMedia;
}
