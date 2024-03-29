package com.alabtaal.thecomputershop.service;



import com.alabtaal.thecomputershop.entity.UserEntity;
import com.alabtaal.thecomputershop.execptions.ResourceNotFoundException;
import com.alabtaal.thecomputershop.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity user;

        try {
            user = userService.findByUsername(username);
        } catch (ResourceNotFoundException e) {
            throw new UsernameNotFoundException("user " + username + " not found ");
        }

        final List<SimpleGrantedAuthority> authorities =  user.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getName()))
                .collect(Collectors.toList());
        
        return UserPrincipal.builder()
                .id(user.getUserId())
                .name(user.getName())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .activationStatus(user.getActivationStatus())
                .authorities(authorities)
                .build();
    }

}
