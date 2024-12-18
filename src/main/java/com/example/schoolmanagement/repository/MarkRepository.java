package com.example.schoolmanagement.repository;

import com.example.schoolmanagement.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, String> {
    @Query("select m from Mark m where m.mark < :mark")
    List<Mark> findByMarkLessThan(@Param("mark") Integer mark);
}
