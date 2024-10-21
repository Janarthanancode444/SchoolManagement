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
@Table(name = "section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "section")
    private String section;
    @ManyToOne
    private Standard standard;
    @ManyToOne
    private User user;
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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Standard getStandard() {
        return standard;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        Section section1 = (Section) o;
        return Objects.equals(id, section1.id) && Objects.equals(section, section1.section) && Objects.equals(standard, section1.standard) && Objects.equals(user, section1.user) && Objects.equals(createdAt, section1.createdAt) && Objects.equals(createdBy, section1.createdBy) && Objects.equals(updatedAt, section1.updatedAt) && Objects.equals(updatedBy, section1.updatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, section, standard, user, createdAt, createdBy, updatedAt, updatedBy);
    }
}
