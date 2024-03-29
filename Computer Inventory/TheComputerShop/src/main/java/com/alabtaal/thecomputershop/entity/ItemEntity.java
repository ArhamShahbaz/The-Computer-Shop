package com.alabtaal.thecomputershop.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;



@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(schema = "inventory" , name = "items")
public class ItemEntity  extends Auditable<String>{

    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID itemId;

    @NotEmpty(message = "name shouldn't be empty.")
    @Column(name = "NAME")
    private String name;

    @NotNull(message = "Sale Rate shouldn't be empty.")
    @Column(name = "SALE_RATE")
    private Double saleRate;

    @NotNull(message = "Purchase Rate shouldn't be empty.")
    @Column(name = "PURCHASE_RATE")
    private Double purchaseRate;


    @Column(name = "GENERATION")
    private String generation;

    @Column(name = "ROM")
    private Double rom;

    @Column(name = "RAM")
    private Double ram;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "STOCK_ITEM")
    private Boolean stockItem;


    @Column(name = "REMARKS")
    private String remarks;



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @ToString.Exclude
    private List<ItemAdjustmentEntity> itemAdjustments;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", orphanRemoval = true)
    @ToString.Exclude
    private List<ItemTransDetailEntity> itemTransDetails;



}