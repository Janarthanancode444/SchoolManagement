package com.example.schoolmanagement.service;

import com.example.schoolmanagement.dto.ResponseDTO;
import com.example.schoolmanagement.dto.SubjectRequestDTO;
import com.example.schoolmanagement.dto.SubjectResponseDTO;
import com.example.schoolmanagement.entity.Subject;
import com.example.schoolmanagement.entity.User;
import com.example.schoolmanagement.exception.BadRequestServiceException;
import com.example.schoolmanagement.repository.SubjectRepository;
import com.example.schoolmanagement.repository.UserRepository;
import com.example.schoolmanagement.util.Constants;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public SubjectService(SubjectRepository subjectRepository, UserRepository userRepository) {
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseDTO createSubject(final SubjectRequestDTO subjectRequestDTO) {
        final User user = this.userRepository.findById(subjectRequestDTO.getUserId()).orElseThrow(() -> new BadRequestServiceException("User not found"));
        user.setId(subjectRequestDTO.getUserId());
        final Subject subject = new Subject();
        subject.setName(subjectRequestDTO.getName());
        subject.setCreatedBy(subjectRequestDTO.getCreatedBy());
        subject.setUpdatedBy(subjectRequestDTO.getUpdatedBy());
        subject.setUser(user);
        return new ResponseDTO(Constants.SUCCESS, this.subjectRepository.save(subject), HttpStatus.CREATED.getReasonPhrase());
    }

    public ResponseDTO retrieve() {
        return new ResponseDTO(Constants.SUCCESS, this.subjectRepository.findAll(), HttpStatus.OK.getReasonPhrase());
    }

    @Transactional
    public ResponseDTO updateSubject(final String id, final SubjectResponseDTO subjectResponseDTO) {
        final Subject existingSubject = this.subjectRepository.findById(id).orElseThrow(() -> new BadRequestServiceException("subject not found"));

        if (subjectResponseDTO.getName() != null) {
            existingSubject.setName(subjectResponseDTO.getName());
        }

        if (subjectResponseDTO.getCreatedBy() != null) {
            existingSubject.setCreatedBy(subjectResponseDTO.getCreatedBy());
        }
        if (subjectResponseDTO.getUpdatedBy() != null) {
            existingSubject.setUpdatedBy(subjectResponseDTO.getUpdatedBy());
        }
        return new ResponseDTO(Constants.SUCCESS, this.subjectRepository.save(existingSubject), HttpStatus.OK.getReasonPhrase());

    }


    @Transactional
    public ResponseDTO remove(final String id) {
        if (!this.subjectRepository.existsById(id)) {
            throw new BadRequestServiceException("Subject Id not found");
        }
        this.subjectRepository.deleteById(id);
        return new ResponseDTO("Successfully deleted.", id, HttpStatus.OK.getReasonPhrase());

    }
}
