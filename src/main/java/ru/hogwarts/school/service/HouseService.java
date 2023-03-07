package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class HouseService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private Long generatedFacultyId = 1L;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(generatedFacultyId);
        faculties.put(generatedFacultyId, faculty);
        generatedFacultyId++;
        return faculty;
    }

    public Faculty getFacultyById(Long facultyId) {
        if (faculties.containsKey(facultyId)) {
            return faculties.get(facultyId);
        }
        return null;
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(Long facultyId) {
        if (faculties.containsKey(facultyId)) {
            return faculties.remove(facultyId);
        }
        return null;
    }

    public Collection<Faculty> getAllFaculties() {
        return faculties.values();
    }

}
