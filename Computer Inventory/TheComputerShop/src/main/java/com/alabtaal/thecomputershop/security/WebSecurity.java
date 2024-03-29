package com.alabtaal.thecomputershop.security;



import com.alabtaal.thecomputershop.security.jwt.JwtAuthEntryPoint;
import com.alabtaal.thecomputershop.security.jwt.JwtAuthTokenFilter;
import com.alabtaal.thecomputershop.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {

    private static final Logger LOG = LoggerFactory.getLogger(WebSecurity.class);

    private final BCryptPasswordEncoder passwordEncoder;
//    private final AccessDeniedHandler accessDeniedHandler;

    private final UserDetailsServiceImpl userDetailsService;


    private final JwtAuthEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthTokenFilter jwtAuthTokenFilter;

    @Value("${app.origins.allowed:http://localhost:3000}")
    private String allowedOrigins;

    private static final String[] AUTH_WHITELIST={
            "/",
            "/favicon.ico",
            "/*/*.gif",
            "/*/*.png",
            "/*/*.svg",
            "/*/*.jpg",
            "/*/*.html",
            "/*/*.css",
            "/*/*.js",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/error"
           };

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        final AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

        return authenticationManagerBuilder.build();


    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint);
//                    exceptionHandling.accessDeniedHandler(accessDeniedHandler);
                })
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                this is authorize request
                .authorizeHttpRequests(auz -> auz
                .requestMatchers("/auth/**")
                        .permitAll()
                .requestMatchers( HttpMethod.PUT,"/users/**")
                        .permitAll()
                .requestMatchers( HttpMethod.POST,"/users/**")
                        .permitAll()
//                .requestMatchers("/items/**")
//                .hasAnyAuthority(RoleName.ROLE_ADMIN.name())
                .anyRequest().authenticated())
//                add filter
                .addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class );


        return httpSecurity.build();

    }



    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        LOG.info("Allowed origins: {}", allowedOrigins);
        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins.split(",")));
//    configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedMethods(
                Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD", "TRACE", "OPTIONS", "PATCH"));
        configuration.setAllowCredentials(true);
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers(AUTH_WHITELIST);
    }
}
