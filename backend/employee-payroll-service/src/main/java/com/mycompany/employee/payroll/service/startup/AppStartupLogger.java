package com.mycompany.employee.payroll.service.startup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupLogger implements ApplicationRunner {
  @Value("${server.port}")
  private String port;

  @Value("${spring.datasource.url}")
  private String datasource;

  @Override
  public void run(ApplicationArguments args) {
    System.out.println("✅ Application started on port: " + port);
    System.out.println("✅ Connected to datasource: " + datasource);
  }

}
