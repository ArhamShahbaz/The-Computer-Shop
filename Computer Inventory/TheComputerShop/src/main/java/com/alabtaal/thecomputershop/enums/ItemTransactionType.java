package com.alabtaal.thecomputershop.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum ItemTransactionType {

    PURCHASE("Purchase"),

    SALE("Sale"),

    PURCHASE_RETURN("Purchase Return"),

    SALE_RETURN("Sale Return");

    private final String value;

    ItemTransactionType(final String value) {
        this.value = value;
    }

    @JsonCreator
    public static ItemTransactionType fromValue(final String value) {
        return Arrays.stream(ItemTransactionType.values())
                .filter(itemTransactionType -> itemTransactionType.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "No constant with value '" + value + "' found in Item Transaction Type enum"));
    }

    @JsonValue
    public String getValue() {
        return value;
    }


}
