package com.alabtaal.thecomputershop.service;




import com.alabtaal.thecomputershop.entity.UserEntity;
import com.alabtaal.thecomputershop.execptions.ResourceNotFoundException;
import com.alabtaal.thecomputershop.execptions.UserResourceNotFountException;
import com.alabtaal.thecomputershop.payload.request.SignupRequest;
import jakarta.validation.constraints.NotNull;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserEntity> findAll();
    UserEntity findById(final UUID id);

    UserEntity findByUsername(final String username) throws ResourceNotFoundException;

    UserEntity save(final UserEntity entity) throws BadRequestException, UserResourceNotFountException;


    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNo(String email);

    UserEntity activeUserById(UUID id) throws BadRequestException, ResourceNotFoundException, UserResourceNotFountException;

    void delete(@NotNull(message = "ID is required") UUID id);
}

