package org.example.testsmartdeltas.mapper;

import org.example.testsmartdeltas.controller.dto.StudentRequestDto;
import org.example.testsmartdeltas.controller.dto.StudentResponseDto;
import org.example.testsmartdeltas.model.Student;

/**
 * Класс для преобразования объектов между {@link StudentRequestDto} и {@link Student}.
 */
public class StudentMapper {

    /**
     * Преобразует объект {@link StudentRequestDto} в объект {@link Student}.
     *
     * @param request объект {@link StudentRequestDto}, содержащий данные для создания объекта {@link Student}.
     * @return объект {@link Student}, созданный на основе данных из {@link StudentRequestDto}.
     */
    public static Student toEntity(StudentRequestDto request) {
        Student student = new Student();
        student.setLastName(request.getLastName());
        student.setFirstName(request.getFirstName());
        student.setMiddleName(request.getMiddleName());
        student.setStudentGroup(request.getStudentGroup());
        student.setAverageGrade(request.getAverageGrade());
        return student;
    }

    /**
     * Преобразует объект {@link Student} в объект {@link StudentResponseDto}.
     *
     * @param student объект {@link Student}, содержащий данные для создания объекта {@link StudentResponseDto}.
     * @return объект {@link StudentResponseDto}, созданный на основе данных из {@link Student}.
     */
    public static StudentResponseDto toResponse(Student student) {
        return StudentResponseDto.builder()
                .id(student.getId())
                .lastName(student.getLastName())
                .firstName(student.getFirstName())
                .middleName(student.getMiddleName())
                .studentGroup(student.getStudentGroup())
                .averageGrade(student.getAverageGrade())
                .build();
    }

    /**
     * Обновляет объект {@link Student} на основе данных из объекта {@link StudentRequestDto}.
     *
     * @param request объект {@link StudentRequestDto}, содержащий данные для обновления объекта {@link Student}.
     * @param student объект {@link Student}, который нужно обновить.
     */
    public static void updateStudentFromDto(StudentRequestDto request, Student student) {
        student.setLastName(request.getLastName());
        student.setFirstName(request.getFirstName());
        student.setMiddleName(request.getMiddleName());
        student.setStudentGroup(request.getStudentGroup());
        student.setAverageGrade(request.getAverageGrade());
    }
}

