package com.mycompany.employee.payroll.service.dao;

import com.mycompany.employee.payroll.service.entity.Factory;
import java.util.Optional;

public interface FactoryDao {

  Optional<Factory> findById(String id);
}
