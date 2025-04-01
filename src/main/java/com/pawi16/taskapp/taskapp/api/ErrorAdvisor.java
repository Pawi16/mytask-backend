package com.pawi16.taskapp.taskapp.api;

import com.pawi16.taskapp.taskapp.exception.BaseException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorAdvisor {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(e.getMessage());
        errorResponse.setStatus(HttpStatus.EXPECTATION_FAILED.value());
        return new ResponseEntity<>(errorResponse,HttpStatus.EXPECTATION_FAILED);
    }

    @Data
    public static class ErrorResponse{
        private LocalDateTime timestamp = LocalDateTime.now();
        private int status;
        private String error;

    }

}
