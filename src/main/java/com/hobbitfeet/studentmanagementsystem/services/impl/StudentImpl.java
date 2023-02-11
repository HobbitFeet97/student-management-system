package com.hobbitfeet.studentmanagementsystem.services.impl;

import com.hobbitfeet.studentmanagementsystem.entities.Student;
import com.hobbitfeet.studentmanagementsystem.repositories.StudentRepository;
import com.hobbitfeet.studentmanagementsystem.services.api.StudentApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentImpl implements StudentApi {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public List<Student> getAllStudents() {
        Iterable<Student> students = studentRepository.findAll();
        List<Student> response = new ArrayList<>();
        for (Student student : students) {
            response.add(student);
        }
        return response;
    }

    @Override
    public Student createStudent(Student student) {
        /*
        When creating a student, ensure created on is set
         */
        LocalDateTime now = LocalDateTime.now();
        student.setCreatedOn(now);
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        /*
        When updating a student, ensure updated on is set and
        id is present
         */
        if (StringUtils.hasText(student.getId())) {
            LocalDateTime now = LocalDateTime.now();
            student.setUpdatedOn(now);
            return studentRepository.save(student);
        }
        return null;
    }
}
