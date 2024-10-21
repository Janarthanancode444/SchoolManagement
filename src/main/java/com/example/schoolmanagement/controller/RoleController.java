package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.dto.ResponseDTO;
import com.example.schoolmanagement.dto.RoleRequestDTO;
import com.example.schoolmanagement.dto.RoleResponseDTO;
import com.example.schoolmanagement.service.RoleService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(final RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create")
    public ResponseDTO createRole(@RequestBody final RoleRequestDTO roleRequestDTO) {
        return this.roleService.createRole(roleRequestDTO);
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieve() {
        return this.roleService.retrieve();
    }


    @PutMapping("/update/{id}")
    public ResponseDTO update(@RequestBody final RoleResponseDTO roleResponseDTO, @PathVariable final String id) {
        return this.roleService.update(roleResponseDTO, id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO remove(@PathVariable final String id) {
        return this.roleService.remove(id);
    }

}
