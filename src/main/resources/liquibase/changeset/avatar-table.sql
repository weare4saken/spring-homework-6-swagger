-- liquibase formatted sql

-- changeset weare4saken:3
CREATE TABLE avatar (
    avatar_id  SERIAL PRIMARY KEY,
    data       BYTEA,
    file_path  VARCHAR(255),
    file_size  BIGINT,
    media_type VARCHAR(255),
    student_id INTEGER REFERENCES student (student_id)
);