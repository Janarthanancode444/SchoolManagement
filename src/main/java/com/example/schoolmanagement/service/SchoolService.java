package com.example.schoolmanagement.service;

import com.example.schoolmanagement.dto.ResponseDTO;
import com.example.schoolmanagement.dto.SchoolRequestDTO;
import com.example.schoolmanagement.dto.SchoolResponseDTO;
import com.example.schoolmanagement.entity.School;
import com.example.schoolmanagement.entity.User;
import com.example.schoolmanagement.exception.BadRequestServiceException;
import com.example.schoolmanagement.repository.SchoolRepository;
import com.example.schoolmanagement.repository.UserRepository;
import com.example.schoolmanagement.util.Constants;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;

    public SchoolService(SchoolRepository schoolRepository, UserRepository userRepository) {
        this.schoolRepository = schoolRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseDTO createSchool(final SchoolRequestDTO schoolRequestDTO) {
        final User user = this.userRepository.findById(schoolRequestDTO.getUserId()).orElseThrow(() -> new BadRequestServiceException("User not found"));
        user.setId(schoolRequestDTO.getUserId());
        final School school = new School();
        school.setName(schoolRequestDTO.getName());
        school.setAddress(schoolRequestDTO.getAddress());
        school.setPhone(schoolRequestDTO.getPhone());
        school.setEmail(schoolRequestDTO.getEmail());
        school.setUser(user);
        school.setCreatedAt(schoolRequestDTO.getCreatedAt());
        school.setCreatedBy(schoolRequestDTO.getCreatedBy());
        school.setUpdatedAt(schoolRequestDTO.getUpdatedAt());
        school.setUpdatedBy(schoolRequestDTO.getUpdatedBy());
        return new ResponseDTO(Constants.SUCCESS, this.schoolRepository.save(school), HttpStatus.OK.getReasonPhrase());
    }


    public ResponseDTO retrieve() {
        return new ResponseDTO(Constants.SUCCESS, this.schoolRepository.findAll(), HttpStatus.OK.getReasonPhrase());
    }

    @Transactional
    public ResponseDTO update(final SchoolResponseDTO responseSchoolDTO, final String id) {
        final School existingSchool = this.schoolRepository.findById(id).orElseThrow(() -> new BadRequestServiceException("School not found"));

        if (responseSchoolDTO.getName() != null) {
            existingSchool.setName(responseSchoolDTO.getName());
        }
        if (responseSchoolDTO.getAddress() != null) {
            existingSchool.setAddress(responseSchoolDTO.getAddress());
        }
        if (responseSchoolDTO.getEmail() != null) {
            existingSchool.setEmail(responseSchoolDTO.getEmail());
        }
        if (responseSchoolDTO.getPhone() != null) {
            existingSchool.setPhone(responseSchoolDTO.getPhone());
        }
        if (responseSchoolDTO.getCreatedBy() != null) {
            existingSchool.setCreatedBy(responseSchoolDTO.getCreatedBy());
        }
        if (responseSchoolDTO.getUpdatedBy() != null) {
            existingSchool.setUpdatedBy(responseSchoolDTO.getUpdatedBy());
        }
        return new ResponseDTO(Constants.SUCCESS, this.schoolRepository.save(existingSchool), HttpStatus.OK.getReasonPhrase());
    }

    @Transactional
    public ResponseDTO remove(String id) {
        if (!this.schoolRepository.existsById(id)) {
            throw new BadRequestServiceException("School Id not found");
        }
        this.schoolRepository.deleteById(id);
        return new ResponseDTO(Constants.DELETED, id, HttpStatus.OK.getReasonPhrase());
    }

    public Page<SchoolRequestDTO> searchSchool(String search, Integer page, Integer size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 5, sort);
        final Page<School> school = this.schoolRepository.searchSchool(search, pageable);
        if (school.isEmpty()) {
            throw new BadRequestServiceException("No data found for the given search criteria");
        }
        return school.map(this::convertToSchoolRequestDTO);
    }

    private SchoolRequestDTO convertToSchoolRequestDTO(final School school) {
        final SchoolRequestDTO schoolRequestDTO = new SchoolRequestDTO();
        schoolRequestDTO.setName(school.getName());
        schoolRequestDTO.setAddress(school.getAddress());
        schoolRequestDTO.setEmail(school.getEmail());
        schoolRequestDTO.setPhone(school.getPhone());
        return schoolRequestDTO;
    }


}


