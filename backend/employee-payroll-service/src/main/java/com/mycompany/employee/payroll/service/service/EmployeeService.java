package com.mycompany.employee.payroll.service.service;

import com.mycompany.employee.payroll.service.dto.EmployeeDto;
import com.mycompany.employee.payroll.service.vo.EmployeeVo;
import org.hibernate.service.spi.ServiceException;

public interface EmployeeService {

  EmployeeVo create(EmployeeDto dto) throws ServiceException;

}
