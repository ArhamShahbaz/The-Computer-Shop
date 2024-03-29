package com.alabtaal.thecomputershop.payload.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@RequiredArgsConstructor
public class ApiResponse<T> {

    private T entity;

    private boolean success;

    private String message;

}
