package com.mycompany.employee.payroll.service.dao;

import com.mycompany.employee.payroll.service.entity.Employee;
import com.mycompany.employee.payroll.service.enums.EmployeeStatusEnum;
import java.util.List;

public interface EmployeeDao {

  Employee save(Employee entity);

  List<Employee> findAll(EmployeeStatusEnum status);

}
