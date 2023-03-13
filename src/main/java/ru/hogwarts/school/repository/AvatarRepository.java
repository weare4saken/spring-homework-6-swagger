package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Avatar;

import java.util.Collection;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
