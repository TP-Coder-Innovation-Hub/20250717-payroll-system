package com.mycompany.employee.payroll.service.dao.impl;

import com.mycompany.employee.payroll.service.dao.FactoryDao;
import com.mycompany.employee.payroll.service.entity.Factory;
import com.mycompany.employee.payroll.service.exception.DataNotFoundException;
import com.mycompany.employee.payroll.service.repository.FactoryRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class FactoryDaoImpl implements FactoryDao {

  private final FactoryRepository repository;

  public FactoryDaoImpl(FactoryRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<Factory> findById(String id) {
    return repository.findById(id);
  }

}
