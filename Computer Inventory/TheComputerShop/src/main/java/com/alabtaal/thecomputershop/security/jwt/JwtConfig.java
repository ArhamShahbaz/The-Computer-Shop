package com.alabtaal.thecomputershop.security.jwt;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class JwtConfig {

    @Value("${security.jwt.uri:/auth/**}")
    private String Uri;


    @Value("${security.jwt.header:Authorization}")
    private String header;


    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;


    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;


    @Value("${security.jwt.secret:JwtSecurityKey")
    private String secret;


}