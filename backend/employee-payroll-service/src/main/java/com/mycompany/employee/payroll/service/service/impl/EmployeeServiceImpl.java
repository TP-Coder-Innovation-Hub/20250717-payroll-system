package com.mycompany.employee.payroll.service.service.impl;

import com.mycompany.employee.payroll.service.dao.EmployeeDao;
import com.mycompany.employee.payroll.service.dao.EmployeePayRateDao;
import com.mycompany.employee.payroll.service.dao.FactoryDao;
import com.mycompany.employee.payroll.service.dto.EmployeeDto;
import com.mycompany.employee.payroll.service.entity.Employee;
import com.mycompany.employee.payroll.service.entity.EmployeePayRate;
import com.mycompany.employee.payroll.service.entity.Factory;
import com.mycompany.employee.payroll.service.enums.EmployeeStatusEnum;
import com.mycompany.employee.payroll.service.exception.BaseServiceException;
import com.mycompany.employee.payroll.service.exception.DataNotFoundException;
import com.mycompany.employee.payroll.service.mapper.EmployeeMapper;
import com.mycompany.employee.payroll.service.service.EmployeeService;
import com.mycompany.employee.payroll.service.vo.EmployeeVo;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeDao employeeDao;
  private final EmployeeMapper employeeMapper;
  private final FactoryDao factoryDao;
  private final EmployeePayRateDao employeePayRateDao;

  public EmployeeServiceImpl(EmployeeDao employeeDao, EmployeeMapper employeeMapper,
      FactoryDao factoryDao, EmployeePayRateDao employeePayRateDao) {
    this.employeeDao = employeeDao;
    this.employeeMapper = employeeMapper;
    this.factoryDao = factoryDao;
    this.employeePayRateDao = employeePayRateDao;
  }

  @Transactional
  @Override
  public EmployeeVo create(EmployeeDto dto) throws BaseServiceException {

    // create employee details
    log.info("Received EmployeeDto: {}", dto);

    Employee entity = employeeMapper.dtoToEntity(dto);
    entity.setStatus(EmployeeStatusEnum.ACTIVE);

    Factory factory = factoryDao.findById(dto.factoryId())
        .orElseThrow(
            () -> new DataNotFoundException("Factory not found with id " + dto.factoryId()));

    entity.setFactory(factory);
    entity = employeeDao.save(entity);

    // create pay rate data
    EmployeePayRate employeePayRate = new EmployeePayRate();
    employeePayRate.setEmployeeId(entity.getId());
    employeePayRate.setPayRate(dto.payRate());
    employeePayRate.setEffectiveStart(Instant.now());
    employeePayRate.setEffectiveEnd(null);
    employeePayRateDao.save(employeePayRate);

    EmployeeVo response = employeeMapper.entityToVo(entity);
    response.setPayRate(dto.payRate());
    return response;
  }


}
