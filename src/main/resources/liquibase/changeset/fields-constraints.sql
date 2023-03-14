-- liquibase formatted sql

-- changeset weare4saken:5
ALTER TABLE faculty ADD UNIQUE (name);

-- changeset weare4saken:6
ALTER TABLE student ADD CONSTRAINT check_positive check (age > 16);