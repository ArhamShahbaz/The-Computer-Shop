package com.alabtaal.thecomputershop.service;


import com.alabtaal.thecomputershop.entity.RoleEntity;
import com.alabtaal.thecomputershop.enums.RoleName;
import com.alabtaal.thecomputershop.execptions.ResourceNotFoundException;

import java.util.UUID;

public interface RoleService {

    RoleEntity findById(final UUID id);

    RoleEntity findByName(final RoleName roleName);

    RoleEntity findByNameThrowError(final RoleName roleName) throws ResourceNotFoundException;
}
