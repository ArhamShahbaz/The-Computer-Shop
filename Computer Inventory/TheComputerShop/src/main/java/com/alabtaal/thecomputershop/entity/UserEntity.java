package com.alabtaal.thecomputershop.entity;


import com.alabtaal.thecomputershop.enums.ActivationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import java.util.UUID;



@Data
@SuperBuilder
@RequiredArgsConstructor
@Entity
@Table(schema = "inventory" , name = "users")
public class UserEntity extends Auditable<String> {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID userId;


    @NotEmpty(message = "name shouldn't be empty.")
    @Column(name = "NAME")
    private String name;

    @NotEmpty(message = "username shouldn't be empty.")
    @Column(name = "USERNAME")
    private String username;

    @NotEmpty(message = "password shouldn't be empty.")
    @Column(name = "PASSWORD")
    private String password;


    @NotEmpty(message = "email shouldn't be empty.")
    @Column(name = "EMAIL")
    private String email;


    @NotEmpty(message = "phone no. shouldn't be empty.")
    @Column(name = "PHONE_NO")
    private String phoneNo;



    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "ACTIVATION_STATUS")
    private ActivationStatus activationStatus;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES",
            schema = "inventory",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;



    public UserEntity clone(){
        return UserEntity
                .builder()
                .name("name")
                .password("password")
                .username("username")
                .email("email")
                .phoneNo("phoneNo")
                .build();
    }




}