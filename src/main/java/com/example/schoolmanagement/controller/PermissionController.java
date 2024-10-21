package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.dto.PermissionRequestDTO;
import com.example.schoolmanagement.dto.ResponseDTO;
import com.example.schoolmanagement.service.PermissionService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(final PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("/create")
    public ResponseDTO createPermission(@RequestBody final PermissionRequestDTO permissionRequestDTO) {
        return this.permissionService.createPermission(permissionRequestDTO);
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieve() {
        return this.permissionService.retrieve();
    }


    @PutMapping("/update/{id}")
    public ResponseDTO update(@PathVariable(value = "id") final String id, @RequestBody final PermissionRequestDTO permissionRequestDTO) {
        return this.permissionService.update(id, permissionRequestDTO);

    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO remove(@PathVariable final String id) {
        return this.permissionService.remove(id);

    }
}

