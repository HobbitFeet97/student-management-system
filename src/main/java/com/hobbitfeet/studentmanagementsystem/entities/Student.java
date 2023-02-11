package com.hobbitfeet.studentmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@Entity
@Table(name = "STUDENT")
public class Student {
    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "IS_ACTIVE")
    private boolean isActive;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATED_ON")
    private LocalDateTime updatedOn;

    protected Student() {}

    public Student(String id, String firstName, String lastName, String email, boolean isActive, String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isActive = isActive;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
    }
}
