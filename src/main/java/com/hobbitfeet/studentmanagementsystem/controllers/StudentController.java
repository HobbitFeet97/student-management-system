package com.hobbitfeet.studentmanagementsystem.controllers;

import com.hobbitfeet.studentmanagementsystem.entities.Student;
import com.hobbitfeet.studentmanagementsystem.exceptions.EntityNotFoundException;
import com.hobbitfeet.studentmanagementsystem.exceptions.ImproperUpdateRequestException;
import com.hobbitfeet.studentmanagementsystem.services.api.StudentApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentApi studentApi;

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentApi.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") String id) throws EntityNotFoundException {
        return ResponseEntity.ok(studentApi.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<Student> createNewStudent(@RequestBody Student newStudent) {
        return ResponseEntity.ok(studentApi.createStudent(newStudent));
    }

    @PutMapping
    public ResponseEntity<Student> updateExistingStudent(@RequestBody Student existingStudent) throws ImproperUpdateRequestException {
        return ResponseEntity.ok(studentApi.updateStudent(existingStudent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable(value = "id") String id) throws EntityNotFoundException {
        return ResponseEntity.ok(studentApi.deleteStudentById(id));
    }
}
