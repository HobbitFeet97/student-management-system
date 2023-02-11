package com.hobbitfeet.studentmanagementsystem.services.impl;

import com.hobbitfeet.studentmanagementsystem.entities.Student;
import com.hobbitfeet.studentmanagementsystem.repositories.StudentRepository;
import com.hobbitfeet.studentmanagementsystem.services.api.StudentApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
