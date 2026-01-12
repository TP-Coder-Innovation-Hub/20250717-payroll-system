package com.mycompany.employee.payroll.service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public record BulkAttendanceRequestDto(
    @NotNull
    LocalDate attendanceDate,

    @NotEmpty(message = "Attendance records must not be empty")
    @Size(min = 1, message = "At least one attendance record is required")
    List<AttendanceItemDto> records

) {

}
