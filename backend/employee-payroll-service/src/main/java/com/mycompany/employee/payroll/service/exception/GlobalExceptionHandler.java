package com.mycompany.employee.payroll.service.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

  @ExceptionHandler(DataNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(
      DataNotFoundException ex, HttpServletRequest req) {
    HttpStatus status = HttpStatus.NOT_FOUND; // 404
    return ResponseEntity.status(status).body(
        new ErrorResponse(
            Instant.now(),
            status.value(),
            status.getReasonPhrase(),
            ex.getMessage(),
            ex.getErrorCode(),
            req.getRequestURI()
        )
    );
  }

  public record ErrorResponse(Instant timestamp, int status, String error, String message,
                              String errorCode,
                              String path) {

  }

}
