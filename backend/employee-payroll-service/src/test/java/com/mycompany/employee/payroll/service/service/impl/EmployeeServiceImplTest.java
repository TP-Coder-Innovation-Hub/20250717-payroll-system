package com.mycompany.employee.payroll.service.service.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mycompany.employee.payroll.service.dao.EmployeeDao;
import com.mycompany.employee.payroll.service.dao.FactoryDao;
import com.mycompany.employee.payroll.service.dto.EmployeeDto;
import com.mycompany.employee.payroll.service.entity.Employee;
import com.mycompany.employee.payroll.service.entity.Factory;
import com.mycompany.employee.payroll.service.enums.EmployeeStatusEnum;
import com.mycompany.employee.payroll.service.mapper.EmployeeMapper;
import com.mycompany.employee.payroll.service.vo.EmployeeVo;
import java.util.UUID;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

  @InjectMocks
  private EmployeeServiceImpl employeeService;

  @Mock
  private EmployeeDao employeeDao;

  @Mock
  private EmployeeMapper employeeMapper;

  @Mock
  private FactoryDao factoryDao;

  @Test
  void create_Employee_Should_Success() {

    // arrange
    final String factoryId = UUID.randomUUID().toString();
    var dto = new EmployeeDto("John", "Engineer", factoryId);
    var entity = new Employee();
    var factory = new Factory();
    var vo = new EmployeeVo("John", "Engineer", "ACTIVE", "F-01");

    when(employeeMapper.dtoToEntity(dto)).thenReturn(entity);
    when(factoryDao.findById(factoryId)).thenReturn(factory);
    when(employeeMapper.entityToVo(entity)).thenReturn(vo);

    // act
    EmployeeVo result = employeeService.create(dto);

    // assert
    Assertions.assertEquals(vo, result);
    Assertions.assertEquals(EmployeeStatusEnum.ACTIVE, entity.getStatus());
    Assertions.assertEquals(factory, entity.getFactory());

    verify(employeeMapper).dtoToEntity(dto);
    verify(factoryDao).findById(factoryId);
    verify(employeeDao).save(entity);
    verify(employeeMapper).entityToVo(entity);
  }

  @Test
  void create_ShouldThrowServiceException_WhenFactoryNotFound() {
    // Arrange
    var dto = new EmployeeDto("John", "Engineer", "invalid-factory-id");
    var entity = new Employee();

    when(employeeMapper.dtoToEntity(dto)).thenReturn(entity);
    when(factoryDao.findById("invalid-factory-id")).thenReturn(null);

    // Act + Assert
    Assertions.assertThrows(ServiceException.class, () -> {
      employeeService.create(dto);
    });

    verify(factoryDao).findById("invalid-factory-id");
  }

}
