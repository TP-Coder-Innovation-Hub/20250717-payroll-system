ALTER TABLE employee_attendance
    ADD COLUMN work_unit numeric(3,1) DEFAULT 1.0;

COMMENT ON COLUMN employee_attendance.work_unit IS '1.0 for full day, 0.5 for half day, 0.0 for absent';

ALTER TABLE employee_attendance
    ADD CONSTRAINT unique_employee_daily_attendance
        UNIQUE (employee_id, attendance_date);