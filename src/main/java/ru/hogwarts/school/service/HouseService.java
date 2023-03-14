package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseService {

    private final FacultyRepository facultyRepository;

    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public FacultyDTO createFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = facultyDTO.toFaculty();
        Faculty createdFaculty = facultyRepository.save(faculty);
        return FacultyDTO.fromFaculty(createdFaculty);
    }

    public FacultyDTO updateFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = facultyDTO.toFaculty();
        Faculty updatedFaculty = facultyRepository.save(faculty);
        return FacultyDTO.fromFaculty(updatedFaculty);
    }

    public void deleteFaculty(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    public FacultyDTO getFacultyById(Long facultyId) {
        return FacultyDTO.fromFaculty(facultyRepository.findById(facultyId).get());
    }

    public Collection<FacultyDTO> getAllFaculties() {
        return facultyRepository.findAll()
                .stream()
                .map(FacultyDTO::fromFaculty)
                .collect(Collectors.toList());
    }

    public Collection<FacultyDTO> getAllFacultiesByColor(String difiniteColor) {
        return facultyRepository.findAllByColor(difiniteColor)
                .stream()
                .map(FacultyDTO::fromFaculty)
                .collect(Collectors.toList());
    }

    public Collection<FacultyDTO> getAllFacultyByName (String name) {
        return facultyRepository.findByNameIgnoreCase(name).stream()
                .map(FacultyDTO::fromFaculty)
                .collect(Collectors.toList());
    }

    public Collection<StudentDTO> getAllStudentsByFacultyId(Long id) {
        List<Student> students = facultyRepository.findById(id).get().getStudents();
        List<StudentDTO> studentsDTO = new ArrayList<>();
        for(Student student : students) {
            StudentDTO studentDTO = StudentDTO.fromStudent(student);
            studentsDTO.add(studentDTO);
        }
        return studentsDTO;
    }

}
