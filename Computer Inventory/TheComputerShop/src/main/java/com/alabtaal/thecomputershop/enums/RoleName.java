package com.alabtaal.thecomputershop.enums;

import com.alabtaal.thecomputershop.execptions.RoleNameNotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum RoleName {
    ROLE_SUPER_ADMIN("Super Admin"),
    ROLE_ADMIN("admin"),
    ROLE_USER("user");

    private final String value;

    RoleName(final String value) {
        this.value = value;
    }

    @JsonCreator
    public static RoleName fromValue(final String value) throws RoleNameNotFoundException {
        return Arrays.stream(RoleName.values())
                .filter(role -> role.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(
                        () -> new RoleNameNotFoundException("Role with value '" + value + "' not found"));
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
