package org.example.testsmartdeltas.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.testsmartdeltas.controller.dto.StudentRequestDto;
import org.example.testsmartdeltas.controller.dto.StudentResponseDto;
import org.example.testsmartdeltas.mapper.StudentMapper;
import org.example.testsmartdeltas.model.Student;
import org.example.testsmartdeltas.repository.StudentRepository;
import org.example.testsmartdeltas.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Реализация сервиса для управления студентами.
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    /**
     * Создает нового студента на основе данных из запроса.
     *
     * @param request объект {@link StudentRequestDto} с данными для создания студента.
     * @return объект {@link StudentResponseDto}, содержащий данные созданного студента.
     */
    @Override
    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto request) {
        Student student = StudentMapper.toEntity(request);

        Student savedStudent = studentRepository.save(student);
        return StudentMapper.toResponse(savedStudent);
    }

    /**
     * Удаляет студента по его идентификатору.
     *
     * @param id идентификатор студента.
     */
    @Transactional
    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    /**
     * Возвращает список всех студентов.
     *
     * @return список объектов {@link StudentResponseDto}, содержащих данные всех студентов.
     */
    @Override
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Обновляет данные студента по его идентификатору.
     *
     * @param id      идентификатор студента, которого нужно обновить.
     * @param request объект {@link StudentRequestDto} с новыми данными для обновления.
     * @return объект {@link StudentResponseDto}, содержащий обновленные данные студента.
     */
    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto request) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student with id " + id + " not found"));

        StudentMapper.updateStudentFromDto(request, existingStudent);

        Student updatedStudent = studentRepository.save(existingStudent);
        return StudentMapper.toResponse(updatedStudent);
    }
}