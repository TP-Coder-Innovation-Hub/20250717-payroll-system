package com.mycompany.employee.payroll.service.controller;

import com.mycompany.employee.payroll.service.dto.BulkAttendanceRequestDto;
import com.mycompany.employee.payroll.service.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/attendances")
public class AttendanceController {

  private final AttendanceService attendanceService;

  public AttendanceController(AttendanceService attendanceService) {
    this.attendanceService = attendanceService;
  }

  @PostMapping("/bulk")
  public ResponseEntity<Void> recordDailyAttendance(@RequestBody BulkAttendanceRequestDto dto) {
    attendanceService.saveDailyAttendance(dto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
