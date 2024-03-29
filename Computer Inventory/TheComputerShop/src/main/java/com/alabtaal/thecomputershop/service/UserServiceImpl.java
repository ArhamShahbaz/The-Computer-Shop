package com.alabtaal.thecomputershop.service;


import com.alabtaal.thecomputershop.entity.UserEntity;
import com.alabtaal.thecomputershop.enums.ActivationStatus;
import com.alabtaal.thecomputershop.execptions.ResourceNotFoundException;
import com.alabtaal.thecomputershop.execptions.UserResourceNotFountException;
import com.alabtaal.thecomputershop.repo.UserRepository;
import com.alabtaal.thecomputershop.utils.Miscellaneous;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }







  @Override
    public UserEntity findById(final UUID id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "Id", id)
        );
    }

    @Override
    public UserEntity findByUsername(final String username) throws ResourceNotFoundException {
        return userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }



    @Override
    public UserEntity save(final UserEntity entity) throws BadRequestException, UserResourceNotFountException {
        Miscellaneous.constraintViolation(entity);
        checkUser(entity);
            try {
            return userRepository.saveAndFlush(entity);
        } catch (final Exception e) {

            throw new BadRequestException(e.getMessage());
        }
    }


    @Override
    public Boolean existsByUsername(final String username) {
        return userRepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    public Boolean existsByEmail(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public Boolean existsByPhoneNo(final String email) {
        return userRepository.existsByPhoneNoIgnoreCase(email);
    }


    @Override
    public UserEntity activeUserById(final UUID id) throws BadRequestException, ResourceNotFoundException, UserResourceNotFountException {
        UserEntity userEntity = findById(id);
        userEntity.setActivationStatus(ActivationStatus.ACTIVE);
        return save(userEntity);
    }

    @Override
    public void delete(@NotNull(message = "ID is required") final UUID id) {


            userRepository.delete(findById(id));

    }


    private void checkUser(final UserEntity entity) throws UserResourceNotFountException {
//        if user username exist then throw error
        if (existsByUsername(entity.getUsername())){
            throw new UserResourceNotFountException("Username",entity.getUsername());
        }
//        if user Phone No exist then throw error
        if (existsByPhoneNo(entity.getPhoneNo())){
            throw new UserResourceNotFountException("Phone No.",entity.getPhoneNo());
        }
//        if user Email exist then throw error
        if (existsByEmail(entity.getEmail())){
            throw new UserResourceNotFountException("Email ",entity.getEmail());
        }
    }

}
