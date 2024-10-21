package com.example.schoolmanagement.repository;

import com.example.schoolmanagement.entity.Standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandardRepository extends JpaRepository<Standard, String> {

}
