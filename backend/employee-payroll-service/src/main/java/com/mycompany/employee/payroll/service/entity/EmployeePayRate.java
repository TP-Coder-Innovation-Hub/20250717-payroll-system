package com.mycompany.employee.payroll.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee_pay_rate")
@Getter
@Setter
public class EmployeePayRate extends BaseEntity {

  @Column(name = "employee_id")
  private String employeeId;

  @Column(name = "pay_rate")
  private BigDecimal payRate;

  @Column(name = "effective_start")
  private Instant effectiveStart;

  @Column(name = "effective_end")
  private Instant effectiveEnd;

}
