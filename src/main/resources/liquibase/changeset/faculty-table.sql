-- liquibase formatted sql

-- changeset weare4saken:1
CREATE TABLE faculty(
    faculty_id  SERIAL PRIMARY KEY,
    color       VARCHAR(50) NOT NULL,
    name        VARCHAR(255) UNIQUE NOT NULL
);