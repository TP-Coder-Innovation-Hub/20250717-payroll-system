package com.mycompany.employee.payroll.service.dao.impl;

import com.mycompany.employee.payroll.service.dao.EmployeeAttendanceDao;
import com.mycompany.employee.payroll.service.entity.EmployeeAttendance;
import com.mycompany.employee.payroll.service.repository.EmployeeAttendanceRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAttendanceDaoImpl implements EmployeeAttendanceDao {

  private final EmployeeAttendanceRepository repository;

  public EmployeeAttendanceDaoImpl(EmployeeAttendanceRepository repository) {
    this.repository = repository;
  }

  @Override
  public void deleteByAttendanceDate(LocalDate date) {
    repository.deleteByAttendanceDate(date);
  }

  @Override
  public void saveAll(List<EmployeeAttendance> entities) {
    repository.saveAll(entities);
  }
}
