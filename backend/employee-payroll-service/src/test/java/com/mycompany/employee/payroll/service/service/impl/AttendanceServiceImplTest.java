package com.mycompany.employee.payroll.service.service.impl;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.mycompany.employee.payroll.service.dao.EmployeeAttendanceDao;
import com.mycompany.employee.payroll.service.dto.AttendanceItemDto;
import com.mycompany.employee.payroll.service.dto.BulkAttendanceRequestDto;
import com.mycompany.employee.payroll.service.enums.AttendanceStatusEnum;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AttendanceServiceImplTest {

  @InjectMocks
  private AttendanceServiceImpl attendanceService;

  @Mock
  private EmployeeAttendanceDao employeeAttendanceDao;

  @Test
  void saveDailyAttendance_Should_Success() {
    // arrange
    LocalDate attendanceDate = LocalDate.now();
    AttendanceItemDto item1 = new AttendanceItemDto(UUID.randomUUID().toString(), "Full Day",
        AttendanceStatusEnum.FULL_DAY.name());
    AttendanceItemDto item2 = new AttendanceItemDto(UUID.randomUUID().toString(), "Half Day",
        AttendanceStatusEnum.HALF_DAY.name());

    BulkAttendanceRequestDto requestDto = new BulkAttendanceRequestDto(
        attendanceDate,
        List.of(item1, item2)
    );

    // act
    attendanceService.saveDailyAttendance(requestDto);

    // assert
    verify(employeeAttendanceDao, times(1)).deleteByAttendanceDate(attendanceDate);
    verify(employeeAttendanceDao, times(1)).saveAll(anyList());
  }
}
