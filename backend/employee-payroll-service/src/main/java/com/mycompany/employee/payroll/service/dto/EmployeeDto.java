package com.mycompany.employee.payroll.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmployeeDto(
    @NotBlank(message = "Full name can't be blank")
    @Size(max = 255, message = "Full name must be less than 255 characters")
    String fullName,

    String position,

    @NotBlank(message = "Factory ID is required")
    String factoryId
) {

}
