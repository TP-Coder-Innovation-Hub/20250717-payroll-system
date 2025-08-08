package com.mycompany.employee.payroll.service.entity;

import com.mycompany.employee.payroll.service.enums.EmployeeStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee extends BaseEntity {

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "position")
  private String position;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private EmployeeStatusEnum status;

  @ManyToOne
  @JoinColumn(name = "factory_id")
  private Factory factory;
}
