package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.dto.ResponseDTO;
import com.example.schoolmanagement.dto.SectionRequestDTO;
import com.example.schoolmanagement.dto.SectionResponseDTO;
import com.example.schoolmanagement.service.SectionService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/section")
public class SectionController {
    private final SectionService sectionService;

    public SectionController(final SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PostMapping("/create")
    public ResponseDTO createSection(@RequestBody final SectionRequestDTO sectionRequestDTO) {
        return this.sectionService.createSection(sectionRequestDTO);

    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieve() {
        return this.sectionService.retrieve();
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateSection(@RequestBody final SectionResponseDTO sectionResponseDTO, @PathVariable final String id) {
        return this.sectionService.updateSection(sectionResponseDTO, id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO remove(@PathVariable final String id) {
        return this.sectionService.remove(id);

    }
}
