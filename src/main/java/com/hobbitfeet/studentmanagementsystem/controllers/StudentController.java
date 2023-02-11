package com.hobbitfeet.studentmanagementsystem.controllers;

import com.hobbitfeet.studentmanagementsystem.entities.Student;
import com.hobbitfeet.studentmanagementsystem.services.api.StudentApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentApi studentApi;
    @GetMapping("/all")
    public ResponseEntity getAllStudents(){
        try {
            return ResponseEntity.ok(studentApi.getAllStudents());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping
    public ResponseEntity createNewStudent(@RequestBody Student newStudent) {
        try {
            return ResponseEntity.ok(studentApi.createStudent(newStudent));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
