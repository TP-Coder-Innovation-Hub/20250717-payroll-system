package com.mycompany.employee.payroll.service.dao.impl;

import com.mycompany.employee.payroll.service.dao.EmployeeDao;
import com.mycompany.employee.payroll.service.entity.Employee;
import com.mycompany.employee.payroll.service.enums.EmployeeStatusEnum;
import com.mycompany.employee.payroll.service.repository.EmployeeRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDaoImpl implements EmployeeDao {

  private final EmployeeRepository repository;

  public EmployeeDaoImpl(EmployeeRepository repository) {
    this.repository = repository;
  }

  @Override
  public Employee save(Employee entity) {
    return repository.save(entity);
  }

  @Override
  public List<Employee> findAll(EmployeeStatusEnum status) {
    return repository.findAllByStatus(status);
  }

}
