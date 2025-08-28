package com.mycompany.employee.payroll.service.exception;

public class DataNotFoundException extends BaseServiceException {

  public DataNotFoundException(String message) {
    super(message, "DATA_NOT_FOUND");
  }

}
