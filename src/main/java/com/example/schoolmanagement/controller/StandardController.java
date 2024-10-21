package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.dto.ResponseDTO;
import com.example.schoolmanagement.dto.StandardRequestDTO;
import com.example.schoolmanagement.dto.StandardResponseDTO;
import com.example.schoolmanagement.service.StandardService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/standard")
public class StandardController {
    private final StandardService standardService;

    private StandardController(final StandardService standardService) {
        this.standardService = standardService;
    }

    @PostMapping("/create")
    public ResponseDTO createClass(@RequestBody final StandardRequestDTO standardRequestDTO) {
        return this.standardService.create(standardRequestDTO);
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieve() {
        return this.standardService.retrieve();
    }

    @PutMapping("/update/{id}")
    public ResponseDTO update(@PathVariable final String id, @RequestBody final StandardResponseDTO standardResponse) {
        return this.standardService.update(id, standardResponse);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO remove(@PathVariable final String id) {
        return this.standardService.remove(id);
    }
}
