package org.example.testsmartdeltas.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для отправки данных студента в ответе.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponseDto {
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String studentGroup;
    private double averageGrade;
}
