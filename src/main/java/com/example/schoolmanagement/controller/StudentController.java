package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.dto.ResponseDTO;
import com.example.schoolmanagement.dto.StudentRequestDTO;
import com.example.schoolmanagement.dto.StudentResponseDTO;
import com.example.schoolmanagement.service.StudentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    private StudentController(final StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/create")
    public ResponseDTO createStudent(@RequestBody final StudentRequestDTO studentRequestDTO) {
        return this.studentService.createStudent(studentRequestDTO);
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieve() {
        return this.studentService.retrieve();
    }

    @PutMapping("/update/{id}")
    public ResponseDTO update(@RequestBody final StudentResponseDTO studentResponseDTO, @PathVariable final String id) {
        return this.studentService.update(studentResponseDTO, id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO remove(@PathVariable(value = "id") final String id) {
        return this.studentService.remove(id);
    }
}
