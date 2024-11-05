package org.example.testsmartdeltas.controller.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для получения данных студента при создании или обновлении.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequestDto {

    @NotNull(message = "Last name cannot be null")
    @Size(min = 1, message = "Last name cannot be empty")
    private String lastName;

    @NotNull(message = "First name cannot be null")
    private String firstName;

    private String middleName;

    @NotNull(message = "Group cannot be null")
    private String studentGroup;

    @NotNull(message = "Average grade cannot be null")
    private Double averageGrade;
}
