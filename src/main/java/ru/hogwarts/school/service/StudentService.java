package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Faculty faculty = facultyRepository.findById(studentDTO.getFacultyId()).get();
        Student student = studentDTO.toStudent();
        student.setFaculty(faculty);
        Student createdStudent = studentRepository.save(student);
        return StudentDTO.fromStudent(createdStudent);
    }

    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Faculty faculty = facultyRepository.findById(studentDTO.getFacultyId()).get();
        Student student = studentDTO.toStudent();
        student.setFaculty(faculty);
        Student updatedStudent = studentRepository.save(student);
        return StudentDTO.fromStudent(updatedStudent);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public StudentDTO getStudentById(Long studentId) {
        return StudentDTO.fromStudent(studentRepository.findById(studentId).get());
    }

    public Collection<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
    }

    public Collection<StudentDTO> getAllStudentsByAge(int difiniteAge) {
        return studentRepository.findAllByAge(difiniteAge)
                .stream()
                .map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
    }

    public Collection<StudentDTO> getAllStudentsByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge)
                .stream()
                .map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
    }

    public FacultyDTO getFacultyByStudentId(Long id) {
        Faculty faculty = facultyRepository.findById(getStudentById(id).getFacultyId()).get();
        List<Student> students = faculty.getStudents();
        List<StudentDTO> studentsDTO = new ArrayList<>();
        for(Student student : students) {
            StudentDTO studentDTO = StudentDTO.fromStudent(student);
            studentDTO.setFacultyId(id);
            studentsDTO.add(studentDTO);
        }
        FacultyDTO facultyDTO = FacultyDTO.fromFaculty(faculty);
        facultyDTO.setStudents(studentsDTO);
        return facultyDTO;
    }

}

