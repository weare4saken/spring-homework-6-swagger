package ru.hogwarts.school.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hogwarts.school.model.Faculty;

import java.util.List;

@Data
@NoArgsConstructor
public class FacultyDTO {

    private Long id;
    private String name;
    private String color;
    private List<StudentDTO> students;

    public static FacultyDTO fromFaculty(Faculty faculty) {
        FacultyDTO dto = new FacultyDTO();
        dto.setId(faculty.getId());
        dto.setName(faculty.getName());
        dto.setColor(faculty.getColor());
        dto.setStudents(faculty.getStudents());
        return dto;
    }

    public Faculty toFaculty() {
        Faculty faculty = new Faculty();
        faculty.setId(this.getId());
        faculty.setName(this.getName());
        faculty.setColor(this.getColor());
        faculty.setStudents(this.getStudents());
        return faculty;
    }

}
