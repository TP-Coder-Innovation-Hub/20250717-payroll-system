package com.mycompany.employee.payroll.service.repository;

import com.mycompany.employee.payroll.service.entity.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactoryRepository extends JpaRepository<Factory, String> {

}
