CREATE TABLE IF NOT EXISTS value_table (
  id SERIAL PRIMARY KEY,
  description varchar(255)
);
INSERT INTO value_table(description) VALUES ('Test 1');
INSERT INTO value_table(description) VALUES ('Test 2');