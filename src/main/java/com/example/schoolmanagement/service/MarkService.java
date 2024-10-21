package com.example.schoolmanagement.service;

import com.example.schoolmanagement.dto.MarkRequestDTO;
import com.example.schoolmanagement.dto.ResponseDTO;
import com.example.schoolmanagement.entity.Mark;
import com.example.schoolmanagement.entity.Student;
import com.example.schoolmanagement.entity.Subject;
import com.example.schoolmanagement.entity.User;
import com.example.schoolmanagement.exception.BadRequestServiceException;
import com.example.schoolmanagement.repository.MarkRepository;
import com.example.schoolmanagement.repository.StudentRepository;
import com.example.schoolmanagement.repository.SubjectRepository;
import com.example.schoolmanagement.repository.UserRepository;
import com.example.schoolmanagement.util.Constants;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkService {
    private final MarkRepository markRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public MarkService(MarkRepository markRepository, StudentRepository studentRepository, UserRepository userRepository, SubjectRepository subjectRepository) {
        this.markRepository = markRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @Transactional
    public ResponseDTO createMark(MarkRequestDTO markRequestDTO) {
        final User user = this.userRepository.findById(markRequestDTO.getUserId()).orElseThrow(() -> new BadRequestServiceException("user not found"));
        user.setId(markRequestDTO.getUserId());
        final Student student = this.studentRepository.findById(markRequestDTO.getStudentId()).orElseThrow(() -> new BadRequestServiceException("Student not found"));
        student.setId(markRequestDTO.getStudentId());
        final Subject subject = this.subjectRepository.findById(markRequestDTO.getSubjectId()).orElseThrow(() -> new BadRequestServiceException("Student not found"));
        subject.setId(markRequestDTO.getSubjectId());
        final Mark mark = new Mark();
        mark.setSubject(subject);
        mark.setStudent(student);
        mark.setUser(user);
        mark.setCreatedBy(markRequestDTO.getCreatedBy());
        mark.setUpdatedBy(markRequestDTO.getUpdatedBy());
        mark.setMark(markRequestDTO.getMark());
        return new ResponseDTO(Constants.CREATED, this.markRepository.save(mark), HttpStatus.CREATED.getReasonPhrase());
    }

    public ResponseDTO retrieve() {
        return new ResponseDTO(Constants.SUCCESS, this.markRepository.findAll(), HttpStatus.OK.getReasonPhrase());
    }

    @Transactional
    public ResponseDTO updateMark(final String id, final MarkRequestDTO markRequestDTO) {
        final Mark existingMark = this.markRepository.findById(id).orElseThrow(() -> new BadRequestServiceException("Mark not found"));

        if (markRequestDTO.getUpdatedBy() != null) {
            existingMark.setUpdatedBy(markRequestDTO.getUpdatedBy());
        }
        if (markRequestDTO.getCreatedBy() != null) {
            existingMark.setCreatedBy(markRequestDTO.getCreatedBy());
        }
        if (markRequestDTO.getMark() != 0) {
            existingMark.setMark(markRequestDTO.getMark());
        }
        return new ResponseDTO(Constants.SUCCESS, this.markRepository.save(existingMark), HttpStatus.OK.getReasonPhrase());
    }

    @Transactional
    public ResponseDTO remove(final String id) {
        if (!this.markRepository.existsById(id)) {
            throw new BadRequestServiceException("Mark Id not found");
        }
        this.markRepository.deleteById(id);
        return new ResponseDTO("Successfully deleted.", id, HttpStatus.OK.getReasonPhrase());
    }

    public ResponseDTO findByMark(final Integer mark) {
        final List<Mark> markResponseDTOList = markRepository.findByMarkLessThan(mark);
        return new ResponseDTO(Constants.SUCCESS, markResponseDTOList, HttpStatus.OK.getReasonPhrase());
    }
}

