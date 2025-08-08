INSERT INTO factory (id, factory_code, factory_name, created_date, last_modified_date)
VALUES
  (gen_random_uuid(), 'F-01', 'Factory-1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (gen_random_uuid(), 'F-02', 'Factory-2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
