package com.mycompany.employee.payroll.service.exception;

public class DataEmptyException extends BaseServiceException {

  public DataEmptyException(String message) {
    super(message, "DATA_EMPTY");
  }

}
