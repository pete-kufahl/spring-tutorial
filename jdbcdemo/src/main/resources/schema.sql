CREATE SCHEMA IF NOT EXISTS jdbcdemo;

SET SCHEMA jdbcdemo;

CREATE TABLE IF NOT EXISTS speaker (id IDENTITY NOT NULL PRIMARY KEY,
 name VARCHAR (100) NOT NULL);

ALTER TABLE speaker ADD COLUMN IF NOT EXISTS skill VARCHAR(90);