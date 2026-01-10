package com.mycompany.employee.payroll.service.dto;

import java.time.LocalDate;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public record BulkAttendanceRequestDto(
    @NotNull
    LocalDate attendanceDate,
    List<AttendanceItemDto> records
) {

}
