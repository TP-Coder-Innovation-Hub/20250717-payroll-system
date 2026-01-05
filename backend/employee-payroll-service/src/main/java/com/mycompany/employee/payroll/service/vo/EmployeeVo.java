package com.mycompany.employee.payroll.service.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeVo {

  private String fullName;

  private String position;

  private String status;

  private String factoryName;

  @JsonIgnore
  private BigDecimal payRate;

}
