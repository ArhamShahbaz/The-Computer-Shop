package com.alabtaal.thecomputershop.model;



import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;


@Data
@SuperBuilder
@NoArgsConstructor
public class ItemModel {

    private UUID itemId;

    @NotEmpty(message = "name shouldn't be empty.")
    private String name;

    @NotEmpty(message = "Sale Rate shouldn't be empty.")
    private Double saleRate;

    @NotEmpty(message = "Purchase Rate shouldn't be empty.")
    private Double purchaseRate;


    private String generation;

    private Double rom;

    private Double ram;

    private String model;

    private Boolean stockItem;

    private Double rate;

    private String remarks;




    public ItemModel clone(ItemModel  model){
        return ItemModel
                .builder()
                .name(model.name)
                .saleRate(model.saleRate)
                .purchaseRate(model.purchaseRate)
                .generation(model.generation)
                .model(model.model)
                .ram(model.ram)
                .rom(model.rom)
                .model(model.model)
                .stockItem(model.stockItem)
                .remarks(model.remarks)
                .build();
    }

}
