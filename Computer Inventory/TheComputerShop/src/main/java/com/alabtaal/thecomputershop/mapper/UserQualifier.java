package com.alabtaal.thecomputershop.mapper;


import com.alabtaal.thecomputershop.entity.RoleEntity;
import com.alabtaal.thecomputershop.enums.RoleName;
import com.alabtaal.thecomputershop.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class UserQualifier {

    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleService roleService;

    @Named(value = "roles")
    public Set<String> setRoles(final Set<RoleEntity> roles) {


        return roles.stream().map(RoleEntity::getName)
                .collect(Collectors.toSet());
    }

    @Named("rolesEntity")
    public Set<RoleEntity> rolesEntity(final Set<RoleName> roleNames){
        return roleNames
                .stream()
                .map(roleService::findByName)
                .collect(Collectors.toSet());
    }



    @Named(value = "password")
    String encryptPassword(final String password) {
        return passwordEncoder.encode(password);
    }
}
