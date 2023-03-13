package ru.hogwarts.school.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String filePath;
    long fileSize;
    String mediaType;
    byte[] data;
    @OneToOne(mappedBy = "id")
    Student student;


}
