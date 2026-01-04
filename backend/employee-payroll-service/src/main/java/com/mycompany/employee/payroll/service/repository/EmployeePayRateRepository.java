package com.mycompany.employee.payroll.service.repository;

import com.mycompany.employee.payroll.service.entity.EmployeePayRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePayRateRepository extends JpaRepository<EmployeePayRate, String> {

}
