package com.alabtaal.thecomputershop.model;



import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ItemTransDetailModel {

    private UUID detailId;

    @NotNull(message = "Item trans. header shouldn't be null")
    private UUID headerId;

    private String name;

    private Double saleRate;

    private Double purchaseRate;

    private String generation;

    private Double rom;

    private Double ram;

    private String model;

    private Boolean stockItem;




    @NotNull(message = "Quantity shouldn't be null")
    private Double quantity;

    private Double discount;


    private String remarks;



}
