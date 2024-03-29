package com.alabtaal.thecomputershop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSummary {

    private UUID userid;
    private String username;
    private String email;
    private String name;
    private Set<String> roles;
}
