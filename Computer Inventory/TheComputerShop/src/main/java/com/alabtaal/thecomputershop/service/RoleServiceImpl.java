package com.alabtaal.thecomputershop.service;


import com.alabtaal.thecomputershop.entity.RoleEntity;
import com.alabtaal.thecomputershop.enums.RoleName;
import com.alabtaal.thecomputershop.execptions.ResourceNotFoundException;
import com.alabtaal.thecomputershop.repo.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleEntity findById(final UUID id) {
        return roleRepository.findById(id)
                .orElse(null);
    }

    @Override
    public RoleEntity findByName(final RoleName roleName) {
        return roleRepository.findByNameIgnoreCase(roleName.name())
                .orElse(null);
    }

    @Override
    public RoleEntity findByNameThrowError(final RoleName roleName) throws ResourceNotFoundException {
        return roleRepository.findByNameIgnoreCase(roleName.name())
                .orElseThrow(() -> new ResourceNotFoundException("Role", "name", roleName.name()));
    }
}
