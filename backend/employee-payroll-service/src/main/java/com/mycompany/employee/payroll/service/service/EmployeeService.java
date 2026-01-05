package com.mycompany.employee.payroll.service.service;

import com.mycompany.employee.payroll.service.dto.EmployeeDto;
import com.mycompany.employee.payroll.service.exception.DataNotFoundException;
import com.mycompany.employee.payroll.service.vo.EmployeeVo;
import java.util.List;

public interface EmployeeService {

  EmployeeVo create(EmployeeDto dto) throws DataNotFoundException;

  List<EmployeeVo> getAll();

}
