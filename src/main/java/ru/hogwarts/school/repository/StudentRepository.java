package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findAllByAge(int age);
    Collection<Student> findByAgeBetween(int minAge, int maxAge);

    @Query(value = "SELECT COUNT(s) AS count FROM student AS s", nativeQuery = true)
    Long getTotalStudentCount();

    @Query(value = "SELECT AVG(s.age) FROM student AS s", nativeQuery = true)
    Long getAverageAge();

    @Query(value = "SELECT * FROM student ORDER BY age ASC LIMIT 5", nativeQuery = true)
    Collection<Student> getFiveYoungestStudents();

}
