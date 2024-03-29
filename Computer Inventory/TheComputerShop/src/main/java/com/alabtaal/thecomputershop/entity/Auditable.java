package com.alabtaal.thecomputershop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Auditable<U> {

    @CreatedBy
    @Column(name = "CREATED_BY", updatable = false)
    protected U createdBy;

    @CreatedDate
//  @Temporal(TIMESTAMP)
    @Column(name = "CREATION_DATE", updatable = false)
    protected LocalDateTime creationDate;

    @LastModifiedBy
    @Column(name = "LAST_UPDATED_BY")
    protected U lastUpdatedBy;

    @LastModifiedDate
//  @Temporal(TIMESTAMP)
    @Column(name = "LAST_UPDATE_DATE")
    protected LocalDateTime lastUpdateDate;
}
