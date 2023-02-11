package com.hobbitfeet.studentmanagementsystem.controllers;

import com.hobbitfeet.studentmanagementsystem.components.api.ExceptionHandlerApi;
import com.hobbitfeet.studentmanagementsystem.entities.Student;
import com.hobbitfeet.studentmanagementsystem.services.api.StudentApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentApi studentApi;
    @Autowired
    ExceptionHandlerApi exceptionHandlerApi;
    @GetMapping("/all")
    public ResponseEntity getAllStudents(){
        try {
            return ResponseEntity.ok(studentApi.getAllStudents());
        } catch (Exception e) {
            return exceptionHandlerApi.handleException(e);
        }
    }
    @PostMapping
    public ResponseEntity createNewStudent(@RequestBody Student newStudent) {
        try {
            return ResponseEntity.ok(studentApi.createStudent(newStudent));
        } catch (Exception e) {
            return exceptionHandlerApi.handleException(e);
        }
    }
    @PutMapping
    public ResponseEntity updateExistingStudent(@RequestBody Student existingStudent) {
        try {
            return ResponseEntity.ok(studentApi.updateStudent(existingStudent));
        } catch (Exception e) {
            return exceptionHandlerApi.handleException(e);
        }
    }
}
