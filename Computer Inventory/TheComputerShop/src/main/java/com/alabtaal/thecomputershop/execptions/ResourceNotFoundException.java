package com.alabtaal.thecomputershop.execptions;


import lombok.Getter;

import java.util.UUID;


@Getter
public class ResourceNotFoundException extends RuntimeException {

    private final String resource;
    private final String fieldName;
    private final Object fieldValue;

    public ResourceNotFoundException(String resource, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : %s" , resource ,fieldName, fieldValue));

        this.resource = resource;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
