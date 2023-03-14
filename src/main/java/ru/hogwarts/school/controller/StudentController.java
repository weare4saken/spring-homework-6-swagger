package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return ResponseEntity.ok(createdStudent);
    }

    @PatchMapping
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(studentDTO);
        if (updatedStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{studentId}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long studentId) {
        StudentDTO student = studentService.getStudentById(studentId);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<Collection<StudentDTO>> getStudents(@RequestParam(required = false) Integer definiteAge,
                                                              @RequestParam(required = false) Integer minAge,
                                                              @RequestParam(required = false) Integer maxAge,
                                                              @RequestParam Integer pageNumber,
                                                              @RequestParam Integer pageSize) {
        if (definiteAge != null) {
            return ResponseEntity.ok(studentService.getAllStudentsByAge(definiteAge));
        }
        if (minAge != null && maxAge != null) {
            return ResponseEntity.ok(studentService.getAllStudentsByAgeBetween(minAge, maxAge));
        }
        if (pageSize <= 0 || pageSize > 50) {
            return ResponseEntity.ok(studentService.getAllStudents(pageNumber, 50));
        }
        return ResponseEntity.ok(studentService.getAllStudents(pageNumber, pageSize));
    }

    @GetMapping("{studentId}/faculty")
    public ResponseEntity<FacultyDTO> getFacultyByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getFacultyByStudentId(studentId));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalStudentCount() {
        return ResponseEntity.ok(studentService.getTotalStudentCount());
    }

    @GetMapping("/average")
    public ResponseEntity<Long> getAverageAge() {
        return ResponseEntity.ok(studentService.getAverageAge());
    }

    @GetMapping("/younger")
    public ResponseEntity<Collection<StudentDTO>> getFiveYoungestStudents() {
        return ResponseEntity.ok(studentService.getFiveYoungestStudents());
    }

}


