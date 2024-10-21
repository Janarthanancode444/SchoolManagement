package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.dto.ResponseDTO;
import com.example.schoolmanagement.dto.UserRequestDTO;
import com.example.schoolmanagement.dto.UserResponseDTO;
import com.example.schoolmanagement.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserController(final UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody final UserRequestDTO userRequestDTO) {
        return this.userService.create(userRequestDTO);
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieve() {
        return this.userService.retrieve();
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateUser(@RequestBody final UserResponseDTO userResponseDTO, @PathVariable final String id) {
        return this.userService.updateUser(userResponseDTO, id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO remove(@PathVariable final String id) {
        return this.userService.removeUser(id);
    }
}
