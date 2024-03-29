package com.alabtaal.thecomputershop.execptions;

import jakarta.validation.constraints.NotEmpty;

public class UserResourceNotFountException extends Exception {

    public UserResourceNotFountException(@NotEmpty(message = "phone no. shouldn't be empty.") String resource , String resourceName) {
        super(String.format("The %s %s is already taken. Please try another %s"  , resource , resourceName  , resource ));


    }
}
