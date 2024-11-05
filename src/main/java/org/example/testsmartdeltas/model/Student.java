package org.example.testsmartdeltas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Сущность, представляющая студента в базе данных.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    /**
     * Идентификатор студента. Генерируется автоматически.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Фамилия студента.
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * Имя студента.
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * Отчество студента.
     */
    @Column(name = "middle_name")
    private String middleName;

    /**
     * Группа студента.
     */
    @Column(name = "student_group", nullable = false)
    private String studentGroup;

    /**
     * Средний балл студента.
     */
    @Column(name = "average_grade", nullable = false)
    private double averageGrade;
}
