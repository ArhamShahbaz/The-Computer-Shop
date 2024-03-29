package com.alabtaal.thecomputershop.execptions;


import com.alabtaal.thecomputershop.payload.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends Exception {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Boolean>> resourceNotFoundException(ResourceNotFoundException ex){
        final String errorMassage = ex.getMessage();
        ApiResponse<Boolean> error = ApiResponse
                .<Boolean>builder()
                .success(false)
                .message(errorMassage)
                .build();

        return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(RoleNameNotFoundException.class)
    public ResponseEntity<ApiResponse<Boolean>> roleNameNotFoundException(RoleNameNotFoundException ex){

        final String errorMessage = ex.getMessage();

        ApiResponse<Boolean> response = ApiResponse
                .<Boolean>builder()
                .message(errorMessage)
                .success(false)
                .build();


        return new ResponseEntity<>(response ,HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ApiResponse<Boolean>> numberFormatException(NumberFormatException ex){

        final String errorMessage = ex.getMessage();

        ApiResponse<Boolean> response = ApiResponse
                .<Boolean>builder()
                .message(errorMessage)
                .success(false)
                .build();


        return new ResponseEntity<>(response ,HttpStatus.BAD_REQUEST);

    }




    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Boolean>> constraintViolationException(ConstraintViolationException ex){

        final String errorMessage = ex.getMessage();

        ApiResponse<Boolean> response = ApiResponse
                .<Boolean>builder()
                .message(errorMessage)
                .success(false)
                .build();


        return new ResponseEntity<>(response ,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Boolean>> httpMessageNotReadableException(HttpMessageNotReadableException ex){

        final String errorMessage = ex.getMessage();

        ApiResponse<Boolean> response = ApiResponse
                .<Boolean>builder()
                .message(errorMessage)
                .success(false)
                .build();


        return new ResponseEntity<>(response ,HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(ActivationStatusNotFoundException.class)
    public ResponseEntity<ApiResponse<Boolean>> activationStatusNotFoundException(ActivationStatusNotFoundException ex){

        final String errorMessage = ex.getMessage();

        ApiResponse<Boolean> response = ApiResponse
                .<Boolean>builder()
                .message(errorMessage)
                .success(false)
                .build();


        return new ResponseEntity<>(response ,HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Boolean>> exception(Exception ex){

        final String errorMessage = ex.getMessage();

        ApiResponse<Boolean> response = ApiResponse
                .<Boolean>builder()
                .message(errorMessage)
                .success(false)
                .build();


        return new ResponseEntity<>(response ,HttpStatus.BAD_REQUEST);

    }

}
