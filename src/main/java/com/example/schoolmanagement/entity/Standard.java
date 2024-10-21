package com.example.schoolmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "standard")
public class Standard {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "total_student")
    private int totalStudent;

    @ManyToOne
    private User User;
    @ManyToOne
    private School school;
    @Column(name = "created_at")
    @CurrentTimestamp
    private Instant createdAt;
    @Column(name = "created_by", nullable = true)
    private String createdBy;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;
    @Column(name = "updated_by", nullable = true)
    private String updatedBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public com.example.schoolmanagement.entity.User getUser() {
        return User;
    }

    public void setUser(com.example.schoolmanagement.entity.User user) {
        User = user;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public int getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(int totalStudent) {
        this.totalStudent = totalStudent;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Standard standard = (Standard) o;
        return totalStudent == standard.totalStudent && Objects.equals(id, standard.id) && Objects.equals(name, standard.name) && Objects.equals(User, standard.User) && Objects.equals(school, standard.school) && Objects.equals(createdAt, standard.createdAt) && Objects.equals(createdBy, standard.createdBy) && Objects.equals(updatedAt, standard.updatedAt) && Objects.equals(updatedBy, standard.updatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, totalStudent, User, school, createdAt, createdBy, updatedAt, updatedBy);
    }
}
