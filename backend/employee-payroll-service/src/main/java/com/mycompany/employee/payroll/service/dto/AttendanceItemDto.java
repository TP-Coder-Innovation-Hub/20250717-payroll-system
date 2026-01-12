package com.mycompany.employee.payroll.service.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record AttendanceItemDto(
    @NotNull
    String employeeId,
    @NotNull
    String remark,
    @NotNull
    String status
) {

}
