package com.alabtaal.thecomputershop.payload.request;


import com.alabtaal.thecomputershop.enums.ActivationStatus;
import com.alabtaal.thecomputershop.enums.RoleName;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;




@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @NotBlank(message = "First name must not be blank")
    @Size(min = 1, max = 50, message = "Name should has min. {min} and max. {max} characters")
    private String name;

    @NotBlank(message = "Username must not be blank")
    @Size(min = 3, max = 50, message = "Username should has min. {min} and max. {max} characters")
    private String username;

    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "phone No. must not be blank")
    private String phoneNo;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, max = 100, message = "Password should has min. {min} and max. {max} characters")
    private String password;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ActivationStatus activationStatus = ActivationStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<RoleName> roles = Set.of(RoleName.ROLE_USER);
}