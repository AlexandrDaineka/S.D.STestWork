package org.example.testsmartdeltas.service;

import org.example.testsmartdeltas.controller.dto.StudentRequestDto;
import org.example.testsmartdeltas.controller.dto.StudentResponseDto;

import java.util.List;

/**
 * Интерфейс сервиса для управления операциями со студентами.
 */
public interface StudentService {

    /**
     * Создает нового студента на основе данных из запроса.
     *
     * @param request объект {@link StudentRequestDto} с данными для создания студента.
     * @return объект {@link StudentResponseDto}, содержащий данные созданного студента.
     */
    StudentResponseDto createStudent(StudentRequestDto request);

    /**
     * Удаляет студента по его идентификатору.
     *
     * @param id идентификатор студента, которого нужно удалить.
     */
    void deleteStudent(Long id);

    /**
     * Возвращает список всех студентов.
     *
     * @return список объектов {@link StudentResponseDto}, содержащих данные всех студентов.
     */
    List<StudentResponseDto> getAllStudents();

    /**
     * Обновляет данные студента на основе его идентификатора.
     *
     * @param id идентификатор студента, которого нужно обновить.
     * @param request объект {@link StudentRequestDto} с новыми данными для обновления.
     * @return объект {@link StudentResponseDto}, содержащий обновленные данные студента.
     */
    StudentResponseDto updateStudent(Long id, StudentRequestDto request);
}
