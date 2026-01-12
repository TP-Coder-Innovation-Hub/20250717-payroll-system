package com.mycompany.employee.payroll.service.enums;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public enum AttendanceStatusEnum {
  FULL_DAY(new BigDecimal("1.0")),
  HALF_DAY(new BigDecimal("0.5")),
  ABSENT(new BigDecimal("0.0"));

  private final BigDecimal defaultWorkUnit;

  AttendanceStatusEnum(BigDecimal defaultWorkUnit) {
    this.defaultWorkUnit = defaultWorkUnit;
  }
}