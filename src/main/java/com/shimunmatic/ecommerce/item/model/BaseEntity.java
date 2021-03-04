package com.shimunmatic.ecommerce.item.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "date_created", updatable = false)
    protected Instant dateCreated;
    @Column(name = "date_updated")
    protected Instant dateUpdated;
    @Column(name = "date_deleted", updatable = false)
    protected Instant dateDeleted;
    @Column(name = "deleted", updatable = false)
    protected Boolean deleted;

    @PrePersist
    private void setDateCreated() {
        dateCreated = Instant.now();
    }

    @PreUpdate
    private void setDateUpdated() {
        dateUpdated = Instant.now();
    }

    @PreRemove
    private void setDateDeleted() {
        deleted = true;
        dateDeleted = Instant.now();
    }
}