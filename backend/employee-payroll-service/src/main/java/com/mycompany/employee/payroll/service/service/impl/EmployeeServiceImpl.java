package com.mycompany.employee.payroll.service.service.impl;

import com.mycompany.employee.payroll.service.dao.EmployeeDao;
import com.mycompany.employee.payroll.service.dao.FactoryDao;
import com.mycompany.employee.payroll.service.dto.EmployeeDto;
import com.mycompany.employee.payroll.service.entity.Employee;
import com.mycompany.employee.payroll.service.entity.Factory;
import com.mycompany.employee.payroll.service.enums.EmployeeStatusEnum;
import com.mycompany.employee.payroll.service.exception.BaseServiceException;
import com.mycompany.employee.payroll.service.exception.DataNotFoundException;
import com.mycompany.employee.payroll.service.mapper.EmployeeMapper;
import com.mycompany.employee.payroll.service.service.EmployeeService;
import com.mycompany.employee.payroll.service.vo.EmployeeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeDao employeeDao;
  private final EmployeeMapper employeeMapper;
  private final FactoryDao factoryDao;

  public EmployeeServiceImpl(EmployeeDao employeeDao, EmployeeMapper employeeMapper,
      FactoryDao factoryDao) {
    this.employeeDao = employeeDao;
    this.employeeMapper = employeeMapper;
    this.factoryDao = factoryDao;
  }

  @Override
  public EmployeeVo create(EmployeeDto dto) throws BaseServiceException {

    log.info("Received EmployeeDto: {}", dto);

    Employee entity = employeeMapper.dtoToEntity(dto);
    entity.setStatus(EmployeeStatusEnum.ACTIVE);

    Factory factory = factoryDao.findById(dto.factoryId())
        .orElseThrow(
            () -> new DataNotFoundException("Factory not found with id " + dto.factoryId()));

    entity.setFactory(factory);
    employeeDao.save(entity);

    return employeeMapper.entityToVo(entity);
  }


}
