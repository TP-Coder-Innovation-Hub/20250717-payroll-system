package com.mycompany.employee.payroll.service.response;

import com.mycompany.employee.payroll.service.vo.EmployeeVo;
import java.time.Instant;
import lombok.Getter;

@Getter
public class EmployeeResponse {

  private final EmployeeVo data;
  private final String message;
  private final Instant timestamp = Instant.now();
  public EmployeeResponse(EmployeeVo data, String message) {
    this.data = data;
    this.message = message;
  }
}
