package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.HouseService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping
    public ResponseEntity<FacultyDTO> createFaculty(@RequestBody FacultyDTO facultyDTO) {
        FacultyDTO createdFaculty = houseService.createFaculty(facultyDTO);
        return ResponseEntity.ok(createdFaculty);
    }

    @PatchMapping
    public ResponseEntity<FacultyDTO> updateFaculty(@RequestBody FacultyDTO facultyDTO) {
        FacultyDTO updatedFaculty = houseService.updateFaculty(facultyDTO);
        if (updatedFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("{facultyId}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long facultyId) {
        houseService.deleteFaculty(facultyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{facultyId}")
    public ResponseEntity<FacultyDTO> getFaculty(@PathVariable Long facultyId) {
        FacultyDTO faculty = houseService.getFacultyById(facultyId);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<Collection<FacultyDTO>> getFaculties(
            @RequestParam(required = false) String definiteColor,
            @RequestParam(required = false) String name) {
        if(definiteColor != null && !definiteColor.isBlank()) {
            return ResponseEntity.ok(houseService.getAllFacultiesByColor(definiteColor));
        }
        if(name != null && !name.isBlank()) {
            return ResponseEntity.ok(houseService.getAllFacultyByName(name));
        }
        return ResponseEntity.ok(houseService.getAllFaculties());
    }

    @GetMapping("{facultyId}/students")
    public ResponseEntity<Collection<StudentDTO>> getAllStudentsByFacultyId (@PathVariable Long facultyId) {
        return ResponseEntity.ok(houseService.getAllStudentsByFacultyId(facultyId));
    }

}
