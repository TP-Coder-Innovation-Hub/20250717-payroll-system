package com.mycompany.employee.payroll.service.dao;

import com.mycompany.employee.payroll.service.entity.Factory;

public interface FactoryDao {

  Factory findById(String id);
}
