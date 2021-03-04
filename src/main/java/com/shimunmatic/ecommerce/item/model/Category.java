package com.shimunmatic.ecommerce.item.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "category", schema = "sc_ecommerce_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Builder
public class Category extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name = "parent_id")
    private Long parentId;
    @JoinColumn(name = "parent_id")
    @Transient
    private Category parent;
    @OneToMany
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private List<Category> subCategories;
}
