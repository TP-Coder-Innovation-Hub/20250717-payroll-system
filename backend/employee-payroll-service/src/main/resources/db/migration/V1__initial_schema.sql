-- V1__initial_schema.sql
-- Created on 2025-07-23 16:45:38 UTC

CREATE TABLE factory (
    id VARCHAR(36) PRIMARY KEY,
    factory_code VARCHAR(100) NOT NULL UNIQUE,
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP
);

CREATE TABLE employee (
    id VARCHAR(36) PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    position VARCHAR(100),
    daily_pay_rate DECIMAL(10,2),
    status VARCHAR(50),
    factory_id VARCHAR(36),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP,
    CONSTRAINT fk_employee_factory FOREIGN KEY (factory_id) REFERENCES factory(id)
);

CREATE TABLE employee_attendance (
    id VARCHAR(36) PRIMARY KEY,
    employee_id VARCHAR(36),
    attendance_date DATE NOT NULL,
    remark TEXT,
    status VARCHAR(50),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP,
    CONSTRAINT fk_attendance_employee FOREIGN KEY (employee_id) REFERENCES employee(id)
);
);

CREATE INDEX idx_attendance_emp_date ON employee_attendance(employee_id, attendance_date);

CREATE TABLE payroll (
    id VARCHAR(36) PRIMARY KEY,
    employee_id VARCHAR(36),
    period_start DATE NOT NULL,
    period_end DATE NOT NULL,
    payroll_date DATE NOT NULL,
    total_working_days INT,
    daily_pay_rate DECIMAL(10,2),
    total_salary DECIMAL(10,2),
    pay_period_type VARCHAR(50),
    generated_by VARCHAR(100),
    notes TEXT,
    generated_at TIMESTAMP,
    CONSTRAINT fk_payroll_employee FOREIGN KEY (employee_id) REFERENCES employee(id)
);
