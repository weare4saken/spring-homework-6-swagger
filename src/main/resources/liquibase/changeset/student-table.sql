-- liquibase formatted sql

-- changeset weare4saken:2
CREATE TABLE student (
    student_id  SERIAL PRIMARY KEY,
    age         INTEGER NOT NULL,
    name        VARCHAR(50) NOT NULL,
    faculty_id  INTEGER REFERENCES faculty(faculty_id)
);
