package com.alabtaal.thecomputershop.entity;


import com.alabtaal.thecomputershop.enums.ItemTransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.ToString.Exclude;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ITEM_TRANS_HEADERS", schema = "INVENTORY")
public class ItemTransHeaderEntity extends Auditable<String> {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "header_id", columnDefinition = "BINARY(16)")
    private UUID headerId;

    @NotNull(message = "Trans. date shouldn't be null")
    @Column(name = "trans_date")
    private LocalDate transDate;

    @NotNull(message = "Trans. type shouldn't be null")
    @Column(name = "TRANS_TYPE")
    @Enumerated(value = EnumType.STRING)
    private ItemTransactionType itemTransactionType;


    @Column(name="discount")
    private Double discount;

    @Column(name="paid")
    private Double paid;

    @Column(name="remaining")
    private Double remaining;

    @Column(name="total")
    private Double total;


    @Column(name = "remarks")
    private String remarks;

    @OneToMany(mappedBy = "header", orphanRemoval = true)
    @Cascade({CascadeType.ALL})
    @Exclude
    private List<ItemTransDetailEntity> ItemTransDetails;

    @Override
    public ItemTransHeaderEntity clone() {
        return ItemTransHeaderEntity
                .builder()
                .transDate(transDate)
                .itemTransactionType(itemTransactionType)
                .remarks(remarks)
                .build();
    }
}
