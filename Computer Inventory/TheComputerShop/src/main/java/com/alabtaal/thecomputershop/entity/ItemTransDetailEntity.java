package com.alabtaal.thecomputershop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;


import java.util.UUID;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ITEM_TRANS_DETAILS", schema = "INVENTORY", uniqueConstraints = {
        @UniqueConstraint(name = "item_trans_details_uk1", columnNames = {"header_id", "item_id"})
})
public class ItemTransDetailEntity extends Auditable<String> {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "detail_id", columnDefinition = "BINARY(16)")
    private UUID detailId;

    @NotNull(message = "Item trans. header shouldn't be null")
    @ManyToOne(optional = false)
    @JoinColumn(name = "header_id")
    private ItemTransHeaderEntity header;

    @NotNull(message = "Item shouldn't be null")
    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id")
    private ItemEntity item;



    @NotNull(message = "Quantity shouldn't be null")
    @DecimalMin(value = "0.0001", message = "Invalid quantity provided")
    @Column(name = "QUANTITY")
    private Double quantity;

    @Column(name = "DISCOUNT")
    private Double discount;


    private String remarks;

    @Override
    protected ItemTransDetailEntity clone() {
        return ItemTransDetailEntity
                .builder()
                .header(header)
                .item(item)
                .quantity(quantity)
                .discount(discount)
                .remarks(remarks)
                .build();
    }

}