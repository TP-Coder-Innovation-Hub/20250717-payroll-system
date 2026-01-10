package com.mycompany.employee.payroll.service.dao;

import com.mycompany.employee.payroll.service.entity.EmployeeAttendance;
import java.time.LocalDate;
import java.util.List;

public interface EmployeeAttendanceDao {

  void deleteByAttendanceDate(LocalDate date);

  void saveAll(List<EmployeeAttendance> entities);
}
