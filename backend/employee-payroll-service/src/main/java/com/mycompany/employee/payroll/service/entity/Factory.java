package com.mycompany.employee.payroll.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "factory")
@Getter
@Setter
public class Factory extends BaseEntity {

  @Column(name = "factory_code")
  private String factoryCode;

  @Column(name = "factory_name")
  private String factoryName;

}
