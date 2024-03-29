package com.alabtaal.thecomputershop.entity;


import com.alabtaal.thecomputershop.enums.ItemAdjustmentType;
import com.alabtaal.thecomputershop.validation.ValidateAdjustmentReason;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ITEM_ADJUSTMENTS", schema = "INVENTORY")
@ValidateAdjustmentReason
public class ItemAdjustmentEntity extends Auditable<String> {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "adjustment_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @NotNull(message = "Adjustment type shouldn't be null")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private ItemAdjustmentType type;

    @NotNull(message = "Adjustment date shouldn't be null")
    @Column(name = "adjustment_date")
    private LocalDateTime adjustmentDate;

    @Column(name = "adjustment_reason")
    private String adjustmentReason;

    @NotNull(message = "Item shouldn't be null")
    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @NotNull(message = "Quantity shouldn't be null")
    @DecimalMin(value = "0.0001", message = "Invalid quantity provided")
    @Column(name = "quantity")
    private Double quantity;

    @Override
    public ItemAdjustmentEntity clone() {

        return ItemAdjustmentEntity
                .builder()
                .type(type)
                .adjustmentDate(adjustmentDate)
                .adjustmentReason(adjustmentReason)
                .item(item)
                .quantity(quantity)
                .build();
    }


}