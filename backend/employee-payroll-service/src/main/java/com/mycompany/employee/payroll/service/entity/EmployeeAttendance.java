package com.mycompany.employee.payroll.service.entity;

import com.mycompany.employee.payroll.service.enums.AttendanceStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee_attendance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeAttendance extends BaseEntity {

  @Column(name = "employee_id")
  private String employeeId;

  @Column(name = "attendance_date")
  private LocalDate attendanceDate;

  @Column(name = "remark")
  private String remark;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private AttendanceStatusEnum status;

  @Column(name = "work_unit")
  private BigDecimal workUnit;

}
