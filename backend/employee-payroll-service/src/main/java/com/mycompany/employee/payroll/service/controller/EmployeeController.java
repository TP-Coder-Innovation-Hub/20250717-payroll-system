package com.mycompany.employee.payroll.service.controller;

import com.mycompany.employee.payroll.service.dto.EmployeeDto;
import com.mycompany.employee.payroll.service.service.EmployeeService;
import com.mycompany.employee.payroll.service.vo.EmployeeVo;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @PostMapping("/create")
  public ResponseEntity<EmployeeVo> create(@Validated @RequestBody EmployeeDto dto)
      throws ServiceException {
    return new ResponseEntity<>(employeeService.create(dto), HttpStatus.CREATED);
  }

}
