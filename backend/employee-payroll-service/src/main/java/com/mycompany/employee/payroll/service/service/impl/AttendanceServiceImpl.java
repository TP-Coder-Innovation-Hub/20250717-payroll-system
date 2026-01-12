package com.mycompany.employee.payroll.service.service.impl;

import com.mycompany.employee.payroll.service.dao.EmployeeAttendanceDao;
import com.mycompany.employee.payroll.service.dto.BulkAttendanceRequestDto;
import com.mycompany.employee.payroll.service.entity.EmployeeAttendance;
import com.mycompany.employee.payroll.service.enums.AttendanceStatusEnum;
import com.mycompany.employee.payroll.service.service.AttendanceService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AttendanceServiceImpl implements AttendanceService {

  private final EmployeeAttendanceDao employeeAttendanceDao;

  public AttendanceServiceImpl(EmployeeAttendanceDao employeeAttendanceDao) {
    this.employeeAttendanceDao = employeeAttendanceDao;
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void saveDailyAttendance(BulkAttendanceRequestDto dto) {
    log.info("Processing bulk attendance for date: {}", dto.attendanceDate());

    employeeAttendanceDao.deleteByAttendanceDate(dto.attendanceDate());

    List<EmployeeAttendance> entities = dto.records().stream()
        .map(item -> {

          AttendanceStatusEnum statusEnum = AttendanceStatusEnum.valueOf(item.status());

          return EmployeeAttendance.builder()
              .employeeId(item.employeeId())
              .attendanceDate(dto.attendanceDate())
              .status(statusEnum)
              .workUnit(statusEnum.getDefaultWorkUnit())
              .remark(item.remark())
              .build();
        })
        .toList();

    employeeAttendanceDao.saveAll(entities);
    log.info("Successfully recorded attendance for {} employees", entities.size());
  }

}
