package com.mycompany.employee.payroll.service.service;

import com.mycompany.employee.payroll.service.dto.BulkAttendanceRequestDto;

public interface AttendanceService {

  void saveDailyAttendance(BulkAttendanceRequestDto dto);
}
