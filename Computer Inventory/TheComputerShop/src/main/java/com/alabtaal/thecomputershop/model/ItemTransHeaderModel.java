package com.alabtaal.thecomputershop.model;


import com.alabtaal.thecomputershop.enums.ItemTransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;


@Data

public class ItemTransHeaderModel {


    private UUID headerId;

    @NotNull(message = "Trans. date shouldn't be null")
    private LocalDate transDate;

    @NotNull(message = "Trans. type shouldn't be null")
    private ItemTransactionType itemTransactionType;


    private Double discount;

    private Double paid;

    private Double remaining;

    private Double total;


    private String remarks;




}
