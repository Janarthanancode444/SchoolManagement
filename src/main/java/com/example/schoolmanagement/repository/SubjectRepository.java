package com.example.schoolmanagement.repository;

import com.example.schoolmanagement.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface SubjectRepository extends JpaRepository<Subject, String> {
}
