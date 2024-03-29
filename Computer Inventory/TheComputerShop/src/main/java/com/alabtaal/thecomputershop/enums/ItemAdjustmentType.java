package com.alabtaal.thecomputershop.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum ItemAdjustmentType {

    OPENING_STOCK("Opening Stock"),
    WASTAGE_ADJUSTMENT("Wastage Adjustment"),
    REVERSAL_ENTRY("Reversal Entry");

    private final String value;

    ItemAdjustmentType(final String value) {
        this.value = value;
    }

    @JsonCreator
    public static ItemAdjustmentType fromValue(final String value) {
        return Arrays.stream(ItemAdjustmentType.values())
                .filter(storeType -> storeType.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "No constant with value '" + value + "' found in ItemAdjustmentType enum"));
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
