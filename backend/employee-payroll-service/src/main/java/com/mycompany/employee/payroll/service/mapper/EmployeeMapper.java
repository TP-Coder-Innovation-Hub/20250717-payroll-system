package com.mycompany.employee.payroll.service.mapper;

import com.mycompany.employee.payroll.service.dto.EmployeeDto;
import com.mycompany.employee.payroll.service.entity.Employee;
import com.mycompany.employee.payroll.service.vo.EmployeeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

  Employee dtoToEntity(EmployeeDto dto);

  @Mapping(target = "factoryName", source = "factory.factoryName")
  EmployeeVo entityToVo(Employee entity);

}

