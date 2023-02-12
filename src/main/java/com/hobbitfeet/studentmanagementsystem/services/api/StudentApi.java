package com.hobbitfeet.studentmanagementsystem.services.api;

import com.hobbitfeet.studentmanagementsystem.entities.Student;
import com.hobbitfeet.studentmanagementsystem.exceptions.EntityNotFoundException;
import com.hobbitfeet.studentmanagementsystem.exceptions.ImproperUpdateRequestException;

import java.util.List;

public interface StudentApi {
    List<Student> getAllStudents();
    Student createStudent(Student student);
    Student updateStudent(Student student) throws ImproperUpdateRequestException;
    Student getStudentById(String id) throws EntityNotFoundException;
    String deleteStudentById(String id) throws EntityNotFoundException;
}
