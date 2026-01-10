package com.mycompany.employee.payroll.service.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mycompany.employee.payroll.service.dao.EmployeeDao;
import com.mycompany.employee.payroll.service.dao.EmployeePayRateDao;
import com.mycompany.employee.payroll.service.dao.FactoryDao;
import com.mycompany.employee.payroll.service.dto.EmployeeDto;
import com.mycompany.employee.payroll.service.entity.Employee;
import com.mycompany.employee.payroll.service.entity.EmployeePayRate;
import com.mycompany.employee.payroll.service.entity.Factory;
import com.mycompany.employee.payroll.service.enums.EmployeeStatusEnum;
import com.mycompany.employee.payroll.service.exception.DataNotFoundException;
import com.mycompany.employee.payroll.service.mapper.EmployeeMapper;
import com.mycompany.employee.payroll.service.vo.EmployeeVo;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

  @InjectMocks
  private EmployeeServiceImpl employeeService;

  @Mock
  private EmployeeDao employeeDao;

  @Mock
  private EmployeeMapper employeeMapper;

  @Mock
  private FactoryDao factoryDao;

  @Mock
  private EmployeePayRateDao employeePayRateDao;

  @Test
  void create_Employee_Should_Success() {

    // arrange
    final String factoryId = UUID.randomUUID().toString();
    var dto = new EmployeeDto("John", "Engineer", factoryId, BigDecimal.valueOf(500));
    var entity = new Employee();
    entity.setId(UUID.randomUUID().toString());
    var factory = new Factory();
    var vo = new EmployeeVo("John", "Engineer", "ACTIVE", "F-01", BigDecimal.valueOf(500));

    when(employeeMapper.dtoToEntity(dto)).thenReturn(entity);
    when(factoryDao.findById(factoryId)).thenReturn(Optional.of(factory));
    when(employeeDao.save(entity)).thenReturn(entity);
    when(employeeMapper.entityToVo(entity)).thenReturn(vo);
    when(employeePayRateDao.save(any(EmployeePayRate.class))).thenReturn(new EmployeePayRate());

    // act
    EmployeeVo result = employeeService.create(dto);

    // assert
    Assertions.assertEquals(vo, result);
    Assertions.assertEquals(EmployeeStatusEnum.ACTIVE, entity.getStatus());
    Assertions.assertEquals(factory, entity.getFactory());

    verify(employeeMapper).dtoToEntity(dto);
    verify(factoryDao).findById(factoryId);
    verify(employeeDao).save(entity);
    verify(employeePayRateDao).save(any(EmployeePayRate.class));
    verify(employeeMapper).entityToVo(entity);
  }

  @Test
  void create_ShouldThrowServiceException_WhenFactoryNotFound() {
    // Arrange
    var dto = new EmployeeDto("John", "Engineer", "invalid-factory-id", BigDecimal.valueOf(500));
    var entity = new Employee();

    when(employeeMapper.dtoToEntity(dto)).thenReturn(entity);
    when(factoryDao.findById("invalid-factory-id")).thenReturn(Optional.empty());

    // Act + Assert
    Assertions.assertThrows(DataNotFoundException.class, () -> {
      employeeService.create(dto);
    });

    verify(factoryDao).findById("invalid-factory-id");
  }

  @Test
  void getAll_Should_ReturnListOfEmployeeVo_WhenEmployeesExist() {
    // Arrange
    var employee = new Employee();
    var employees = List.of(employee);
    var employeeVo = new EmployeeVo("John", "Engineer", "ACTIVE", "F-01", BigDecimal.valueOf(500));
    var employeeVos = List.of(employeeVo);

    when(employeeDao.findAll(EmployeeStatusEnum.ACTIVE)).thenReturn(employees);
    when(employeeMapper.entityListToVoList(employees)).thenReturn(employeeVos);

    // Act
    var result = employeeService.getAll();

    // Assert
    Assertions.assertNotNull(result);
    Assertions.assertEquals(1, result.size());
    verify(employeeDao).findAll(EmployeeStatusEnum.ACTIVE);
    verify(employeeMapper).entityListToVoList(employees);
  }

  @Test
  void getAll_Should_ReturnEmptyList_WhenNoEmployeesExist() {
    // Arrange
    when(employeeDao.findAll(EmployeeStatusEnum.ACTIVE)).thenReturn(List.of());

    // Act & Assert
    Assertions.assertTrue(employeeService.getAll().isEmpty());
    verify(employeeDao).findAll(EmployeeStatusEnum.ACTIVE);
  }
}
