package com.alabtaal.thecomputershop.controller;



import com.alabtaal.thecomputershop.model.UserPrincipal;
import com.alabtaal.thecomputershop.model.UserSummary;
import com.alabtaal.thecomputershop.payload.request.LoginRequest;
import com.alabtaal.thecomputershop.payload.response.ApiResponse;
import com.alabtaal.thecomputershop.payload.response.JwtAuthenticationResponse;
import com.alabtaal.thecomputershop.security.CurrentUser;
import com.alabtaal.thecomputershop.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@RestController
@RequestMapping(value = "auth")
public class AuthController {


    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;


    @PostMapping(value = "login")
    public ResponseEntity<ApiResponse<JwtAuthenticationResponse>> login(@RequestBody LoginRequest userNamePassword) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userNamePassword.getUsername(),
                        userNamePassword.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String jwt = jwtProvider.generateJwt(authentication);

        ApiResponse<JwtAuthenticationResponse> apiResponse = ApiResponse
                .<JwtAuthenticationResponse>builder()
                .success(true)
                .entity(new JwtAuthenticationResponse(jwt))
                .message("Login Successfully")
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value = "user/me")
    private ResponseEntity<ApiResponse<UserSummary>> currentUser(@CurrentUser UserPrincipal currentUser) {

        if (currentUser == null){
            throw new RuntimeException("user is null");
        }

        final Set<String> authorities = currentUser
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        final UserSummary userSummary = UserSummary
                .builder()
                .userid(currentUser.getId())
                .name(currentUser.getName())
                .username(currentUser.getUsername())
                .email(currentUser.getEmail())
                .roles(authorities)
                .build();

        ApiResponse<UserSummary> apiResponse = ApiResponse
                .<UserSummary>builder()
                .success(true).entity(userSummary)
                .message("User sent")
                .build();

        return ResponseEntity.ok(apiResponse);



    }

}
