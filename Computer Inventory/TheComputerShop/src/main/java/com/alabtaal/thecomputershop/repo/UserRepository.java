package com.alabtaal.thecomputershop.repo;

import com.alabtaal.thecomputershop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {


    Optional<UserEntity> findByUsernameIgnoreCase(final String username);


    Boolean existsByUsernameIgnoreCase(String username);

    Boolean existsByEmailIgnoreCase(String email);


    Boolean existsByPhoneNoIgnoreCase(String email);
}
