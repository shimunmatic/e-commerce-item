package com.shimunmatic.ecommerce.item.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "item_media", schema = "sc_ecommerce_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Builder
public class ItemMedia extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "path")
    private String path;
    @Column(name = "type")
    private String type;
    @Column(name = "item_id")
    private Long itemId;
    @JoinColumn(name = "item_id")
    @Transient
    private Item item;
}
