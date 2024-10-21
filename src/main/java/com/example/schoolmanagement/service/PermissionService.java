package com.example.schoolmanagement.service;

import com.example.schoolmanagement.dto.PermissionRequestDTO;
import com.example.schoolmanagement.dto.ResponseDTO;
import com.example.schoolmanagement.entity.Permission;
import com.example.schoolmanagement.entity.Teacher;
import com.example.schoolmanagement.entity.User;
import com.example.schoolmanagement.exception.BadRequestServiceException;
import com.example.schoolmanagement.repository.PermissionRepository;
import com.example.schoolmanagement.repository.TeacherRepository;
import com.example.schoolmanagement.repository.UserRepository;
import com.example.schoolmanagement.util.Constants;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    public PermissionService(PermissionRepository permissionRepository, UserRepository userRepository, TeacherRepository teacherRepository) {
        this.permissionRepository = permissionRepository;
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
    }

    @Transactional
    public ResponseDTO createPermission(final PermissionRequestDTO permissionRequestDTO) {
        final User user = this.userRepository.findById(permissionRequestDTO.getUserId()).orElseThrow(() -> new BadRequestServiceException("user not found"));
        user.setId(permissionRequestDTO.getUserId());
        final Teacher teacher = this.teacherRepository.findById(permissionRequestDTO.getTeacherId()).orElseThrow(() -> new BadRequestServiceException("Teacher not found"));
        teacher.setId(permissionRequestDTO.getTeacherId());
        final Permission permission = new Permission();
        permission.setCreatedBy(permissionRequestDTO.getCreatedBy());
        permission.setUpdatedBy(permissionRequestDTO.getUpdatedBy());
        permission.setUser(user);
        permission.setTeacher(teacher);
        return new ResponseDTO(Constants.CREATED, this.permissionRepository.save(permission), HttpStatus.CREATED.getReasonPhrase());
    }

    public ResponseDTO retrieve() {
        return new ResponseDTO(Constants.SUCCESS, this.permissionRepository.findAll(), HttpStatus.OK.getReasonPhrase());
    }


    @Transactional
    public ResponseDTO update(final String id, final PermissionRequestDTO permissionRequestDTO) {
        final Permission existingSchool = this.permissionRepository.findById(id).orElseThrow(() -> new BadRequestServiceException("Permission not found"));

        if (permissionRequestDTO.getUpdatedBy() != null) {
            existingSchool.setUpdatedBy(permissionRequestDTO.getUpdatedBy());
        }
        if (permissionRequestDTO.getCreatedBy() != null) {
            existingSchool.setCreatedBy(permissionRequestDTO.getCreatedBy());
        }

        return new ResponseDTO(Constants.SUCCESS, this.permissionRepository.save(existingSchool), HttpStatus.OK.getReasonPhrase());
    }

    @Transactional
    public ResponseDTO remove(final String id) {
        if (!this.permissionRepository.existsById(id)) {
            throw new BadRequestServiceException("Permission Id not found");
        }
        this.permissionRepository.deleteById(id);
        return new ResponseDTO("Successfully deleted.", id, HttpStatus.OK.getReasonPhrase());
    }

}
