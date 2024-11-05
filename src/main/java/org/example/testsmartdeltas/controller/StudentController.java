package org.example.testsmartdeltas.controller;

import lombok.RequiredArgsConstructor;
import org.example.testsmartdeltas.controller.dto.StudentRequestDto;
import org.example.testsmartdeltas.controller.dto.StudentResponseDto;
import org.example.testsmartdeltas.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDto createStudent(@RequestBody StudentRequestDto request) {
        return studentService.createStudent(request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(@RequestHeader("student_id") Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponseDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponseDto updateStudent(
            @PathVariable Long id,
            @RequestBody StudentRequestDto request) {
            return studentService.updateStudent(id, request);

    }
}