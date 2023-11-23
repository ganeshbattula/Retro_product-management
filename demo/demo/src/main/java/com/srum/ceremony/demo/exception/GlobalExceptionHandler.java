package com.srum.ceremony.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;

import com.srum.ceremony.demo.exception.RetrospectiveNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    
    @ExceptionHandler(RetrospectiveNotFoundException.class)
    public ResponseEntity<?> handleRetrospectiveNotFoundException(RetrospectiveNotFoundException ex) {
        logger.error("Error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
