CREATE EXTENSION IF NOT EXISTS btree_gist;

CREATE TABLE factory (
    id                  VARCHAR(36) PRIMARY KEY,
    factory_code        VARCHAR(100) NOT NULL UNIQUE,
    factory_name        VARCHAR(255),
    created_date        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE employee (
    id                  VARCHAR(36) PRIMARY KEY,
    full_name           VARCHAR(255) NOT NULL,
    position            VARCHAR(100),
    status              VARCHAR(50),
    factory_id          VARCHAR(36),
    created_date        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_employee_factory FOREIGN KEY (factory_id) REFERENCES factory(id)
);

CREATE TABLE employee_pay_rate (
    id                  VARCHAR(36) PRIMARY KEY,
    employee_id         VARCHAR(36) NOT NULL,
    pay_rate            DECIMAL(10,2) NOT NULL,
    effective_start     DATE NOT NULL,
    effective_end       DATE,
    created_date        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_payrate_employee FOREIGN KEY (employee_id) REFERENCES employee(id),
    CONSTRAINT chk_effective_dates CHECK (effective_end IS NULL OR effective_end >= effective_start)
);

-- Prevent overlapping ranges for the same employee
ALTER TABLE employee_pay_rate
    ADD CONSTRAINT employee_pay_rate_no_overlap
    EXCLUDE USING gist (
        employee_id WITH =,
        daterange(effective_start, COALESCE(effective_end, '9999-12-31')) WITH &&
    );

CREATE INDEX idx_employee_payrate_range
    ON employee_pay_rate (employee_id, effective_start);

CREATE TABLE employee_attendance (
    id                  VARCHAR(36) PRIMARY KEY,
    employee_id         VARCHAR(36) NOT NULL,
    attendance_date     DATE NOT NULL,
    remark              TEXT,
    status              VARCHAR(50),
    created_date        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_attendance_employee FOREIGN KEY (employee_id) REFERENCES employee(id)
);

CREATE INDEX idx_attendance_emp_date
    ON employee_attendance (employee_id, attendance_date);

CREATE TABLE payroll (
    id                  VARCHAR(36) PRIMARY KEY,
    employee_id         VARCHAR(36) NOT NULL,
    period_start        DATE NOT NULL,
    period_end          DATE NOT NULL,
    payroll_date        DATE NOT NULL,
    total_working_days  INT,
    total_salary        DECIMAL(10,2),
    pay_period_type     VARCHAR(50),
    generated_by        VARCHAR(100),
    notes               TEXT,
    generated_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_payroll_employee FOREIGN KEY (employee_id) REFERENCES employee(id)
);
