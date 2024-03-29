package com.alabtaal.thecomputershop.security.jwt;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

// difference between Configuration and Component ?
@Configuration
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    private static final Logger LOG = LoggerFactory.getLogger(JwtAuthEntryPoint.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException{
        final String errorMessage =
                StringUtils.isNoneBlank(e.getMessage()) ? e.getMessage() : "ERROR -> UNAUTHORIZED";
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, errorMessage);
    }
}
