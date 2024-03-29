package com.alabtaal.thecomputershop.repo;


import com.alabtaal.thecomputershop.entity.RoleEntity;
import com.alabtaal.thecomputershop.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

    Optional<RoleEntity> findByNameIgnoreCase(final String roleName);
}
