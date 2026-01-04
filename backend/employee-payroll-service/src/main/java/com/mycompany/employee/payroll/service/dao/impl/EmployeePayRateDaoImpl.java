package com.mycompany.employee.payroll.service.dao.impl;

import com.mycompany.employee.payroll.service.dao.EmployeePayRateDao;
import com.mycompany.employee.payroll.service.entity.EmployeePayRate;
import com.mycompany.employee.payroll.service.repository.EmployeePayRateRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeePayRateDaoImpl implements EmployeePayRateDao {

  private final EmployeePayRateRepository repository;

  public EmployeePayRateDaoImpl(EmployeePayRateRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(EmployeePayRate entity) {
    repository.save(entity);
  }

}
