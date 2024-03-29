package com.alabtaal.thecomputershop.security.jwt;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// difference between Configuration and Component ?
@Component
@RequiredArgsConstructor
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

    private final JwtProvider jwtProvider;

    private final UserDetailsService userDetailsService;
    private final JwtConfig jwtConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String jwt = getJwt(request);
            if (StringUtils.isNoneBlank(jwt) && jwtProvider.validateJwtToken(jwt)) {
                final String userName = jwtProvider.getUsernameFromJwt(jwt);
                final UserDetails user = userDetailsService.loadUserByUsername(userName);
                final UsernamePasswordAuthenticationToken
                        authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);


            }
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
        filterChain.doFilter(request, response);


    }

    private String getJwt(HttpServletRequest request) {

       final String authHeader = request.getHeader(jwtConfig.getHeader());


        if (StringUtils.isNoneBlank(authHeader) && authHeader.startsWith(jwtConfig.getPrefix())) {
            return authHeader.replace(jwtConfig.getPrefix(), "");
        }

        return null;
    }
}
