package com.alabtaal.thecomputershop.security;


import com.alabtaal.thecomputershop.payload.response.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
        throws IOException, ServletException {

        String user = "user";

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            user = authentication.getName();
        }



        String errorMessage = user + " is not authorized to access protected URL : " +request.getRequestURI();

        ApiResponse<String> apiResponse = ApiResponse
            .<String>builder()
            .success(false)
            .entity(null)
            .message(errorMessage)
            .build();




        throw new AccessDeniedException(errorMessage);
    }




    private static String toJSON(ApiResponse<String> apiResponse ){
        String result = "";
        StringBuilder sb = new StringBuilder();
        


        return result;
    }
}
