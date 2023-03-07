package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping()
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student);
        if (updatedStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long studentId) {
        Student deletedStudent = studentService.deleteStudent(studentId);
        if (deletedStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(deletedStudent);
    }

    @GetMapping("/age/{definiteAge}")
    public ResponseEntity<Collection<Student>> getStudentsByDefiniteAge(@PathVariable int definiteAge) {
        return ResponseEntity.ok(studentService.getAllStudents()
                .stream()
                .filter(student -> student.getAge() == definiteAge)
                .collect(Collectors.toList()));
    }

}

