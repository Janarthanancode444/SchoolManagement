package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.dto.ResponseDTO;
import com.example.schoolmanagement.dto.SubjectRequestDTO;
import com.example.schoolmanagement.dto.SubjectResponseDTO;
import com.example.schoolmanagement.service.SubjectService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectService subjectService;

    private SubjectController(final SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/create")
    public ResponseDTO createSubject(@RequestBody final SubjectRequestDTO subjectRequestDTO) {
        return this.subjectService.createSubject(subjectRequestDTO);
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieve() {
        return this.subjectService.retrieve();
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateSubject(@PathVariable final String id, @RequestBody final SubjectResponseDTO SubjectResponseDTO) {
        return this.subjectService.updateSubject(id, SubjectResponseDTO);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO remove(@PathVariable final String id) {
        return this.subjectService.remove(id);
    }
}
