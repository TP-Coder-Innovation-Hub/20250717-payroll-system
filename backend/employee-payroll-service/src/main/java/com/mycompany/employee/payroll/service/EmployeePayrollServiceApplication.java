package com.mycompany.employee.payroll.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = "com.mycompany.employee.payroll.service")
public class EmployeePayrollServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(EmployeePayrollServiceApplication.class, args);
  }

}
