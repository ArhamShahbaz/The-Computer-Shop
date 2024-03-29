package com.alabtaal.thecomputershop.controller;


import com.alabtaal.thecomputershop.entity.UserEntity;
import com.alabtaal.thecomputershop.execptions.UserResourceNotFountException;
import com.alabtaal.thecomputershop.mapper.UserMapper;
import com.alabtaal.thecomputershop.model.UserModel;
import com.alabtaal.thecomputershop.model.UserSummary;
import com.alabtaal.thecomputershop.payload.request.SignupRequest;
import com.alabtaal.thecomputershop.payload.response.ApiResponse;
import com.alabtaal.thecomputershop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;


    @PostMapping(value = "save")
    public ResponseEntity<ApiResponse<UserSummary>> save(@RequestBody SignupRequest request) throws BadRequestException, UserResourceNotFountException {


        UserEntity entity = userMapper.toEntity(request);

        UserEntity savedData =   userService.save(entity);

        UserSummary userSummary = userMapper.toUserSummary(savedData);

        ApiResponse<UserSummary> userSavedSuccessfully = ApiResponse
                .<UserSummary>builder()
                .entity(userSummary)
                .success(true)
                .message("USER SAVED SUCCESSFULLY.")
                .build();


        return ResponseEntity.ok(userSavedSuccessfully);

    }









}
