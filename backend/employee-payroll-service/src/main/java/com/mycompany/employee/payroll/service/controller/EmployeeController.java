package com.mycompany.employee.payroll.service.controller;

import com.mycompany.employee.payroll.service.dto.EmployeeDto;
import com.mycompany.employee.payroll.service.exception.BaseServiceException;
import com.mycompany.employee.payroll.service.response.EmployeeResponse;
import com.mycompany.employee.payroll.service.service.EmployeeService;
import com.mycompany.employee.payroll.service.vo.EmployeeVo;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @PostMapping
  public ResponseEntity<EmployeeResponse> create(@Validated @RequestBody EmployeeDto dto)
      throws BaseServiceException {
    EmployeeVo vo = employeeService.create(dto);
    EmployeeResponse response = new EmployeeResponse(vo, "Employee created successfully");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping
  public ResponseEntity<List<EmployeeVo>> getAll() {
    return ResponseEntity.ok(employeeService.getAll());
  }

}
