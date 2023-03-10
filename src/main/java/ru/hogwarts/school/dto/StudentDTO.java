package ru.hogwarts.school.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hogwarts.school.model.Student;

@Data
@NoArgsConstructor
public class StudentDTO {

    private Long id;
    private String name;
    private Integer age;
    private FacultyDTO faculty;

    public static StudentDTO fromStudent(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        dto.setFaculty(FacultyDTO.fromFaculty(student.getFaculty()));
        return dto;
    }

    public Student toStudent() {
        Student student = new Student();
        student.setId(this.getId());
        student.setName(this.getName());
        student.setAge(this.getAge());
        student.setFaculty(this.getFaculty().toFaculty());
        return student;
    }

}
