package com.alabtaal.thecomputershop.model;

import com.alabtaal.thecomputershop.entity.ItemEntity;
import com.alabtaal.thecomputershop.enums.ItemAdjustmentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ItemAdjustmentModel {


    private UUID id;

    @NotNull(message = "Adjustment type shouldn't be null")
    private ItemAdjustmentType type;

    @NotNull(message = "Adjustment date shouldn't be null")
    private String adjustmentDate;

    private String adjustmentReason;

    private UUID itemId;

    private String name;

    private String generation;

    private Double rom;

    private Double ram;

    private String model;


    @NotNull(message = "Quantity shouldn't be null")
    private Double quantity;

}
