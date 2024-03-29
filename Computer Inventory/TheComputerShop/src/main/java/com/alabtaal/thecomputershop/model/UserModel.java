package com.alabtaal.thecomputershop.model;


import lombok.Data;

import java.util.UUID;

@Data
public class UserModel {

    private UUID userId;

    private String name;

    private String username;

    private String password;

    private String email;

    private String phoneNo;



}
