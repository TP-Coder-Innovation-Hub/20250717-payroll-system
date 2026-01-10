package com.mycompany.employee.payroll.service.repository;

import com.mycompany.employee.payroll.service.entity.EmployeeAttendance;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, String> {

  @Modifying
  @Query("DELETE FROM EmployeeAttendance ea WHERE ea.attendanceDate = ?1")
  void deleteByAttendanceDate(LocalDate date);

}
