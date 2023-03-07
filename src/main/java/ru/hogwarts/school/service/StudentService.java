package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private Long generatedStudentId = 1L;

    public Student createStudent(Student student) {
        student.setId(generatedStudentId);
        students.put(generatedStudentId, student);
        generatedStudentId++;
        return student;
    }

    public Student getStudentById(Long studentId) {
        if (students.containsKey(studentId)) {
            return students.get(studentId);
        }
        return null;
    }

    public Student updateStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(Long studentId) {
        if (students.containsKey(studentId)) {
            return students.remove(studentId);
        }
        return null;
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }
}

