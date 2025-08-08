package com.mycompany.employee.payroll.service.dao.impl;

import com.mycompany.employee.payroll.service.dao.EmployeeDao;
import com.mycompany.employee.payroll.service.entity.Employee;
import com.mycompany.employee.payroll.service.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDaoImpl implements EmployeeDao {

  private final EmployeeRepository repository;

  public EmployeeDaoImpl(EmployeeRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(Employee entity) {
    repository.save(entity);
  }
}
