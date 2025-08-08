package com.mycompany.employee.payroll.service.exception;

import lombok.Getter;

@Getter
public class BaseServiceException extends RuntimeException {

  private final String errorCode;

  public BaseServiceException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public BaseServiceException(String message, String errorCode, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }

}
