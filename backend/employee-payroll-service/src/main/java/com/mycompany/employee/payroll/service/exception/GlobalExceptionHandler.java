package com.mycompany.employee.payroll.service.exception;

import java.time.Instant;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

  @ExceptionHandler(DataEmptyException.class)
  public ResponseEntity<Map<String, Object>> handleDataEmptyException(DataEmptyException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        Map.of("timestamp", Instant.now(), "status", HttpStatus.BAD_REQUEST.value(), "error",
            ex.getErrorCode(), "message", ex.getMessage()));
  }

  @ExceptionHandler(BaseServiceException.class)
  public ResponseEntity<Map<String, Object>> handleBaseServiceException(BaseServiceException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        Map.of("timestamp", Instant.now(), "status", HttpStatus.BAD_REQUEST.value(), "error",
            ex.getErrorCode(), "message", ex.getMessage()));
  }
  
}
