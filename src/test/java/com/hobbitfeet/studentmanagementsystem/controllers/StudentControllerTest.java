package com.hobbitfeet.studentmanagementsystem.controllers;

import com.hobbitfeet.studentmanagementsystem.entities.Student;
import com.hobbitfeet.studentmanagementsystem.exceptions.EntityNotFoundException;
import com.hobbitfeet.studentmanagementsystem.services.api.StudentApi;
import com.hobbitfeet.studentmanagementsystem.services.impl.StudentImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class StudentControllerTest {

    private StudentController controller;

    @Mock
    private StudentApi studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new StudentController(studentService);
    }

    @Test
    void whenGetAllStudents_thenResponseEntity200_andBodyTypeOfList() {
        //Given
        given(studentService.getAllStudents()).willReturn(List.of());
        //When
        ResponseEntity<List<Student>> actual = controller.getAllStudents();
        //Then
        HttpStatus expectedStatus = HttpStatus.OK;
        assertThat(actual.getStatusCode()).isEqualTo(expectedStatus);
        assertThat(actual.getBody()).isInstanceOf(List.class);
    }

    @Test
    void whenGetStudentById_andStudentExists_responseCodeShouldBe200() {
        //Given
        String targetId = "123";
        given(studentService.getStudentById(targetId)).willReturn(Student.builder().id(targetId).build());
        //When
        ResponseEntity<Student> actual = controller.getStudentById(targetId);
        //Then
        HttpStatus expectedStatus = HttpStatus.OK;
        assertThat(actual.getStatusCode()).isEqualTo(expectedStatus);
        assertThat(actual.getBody()).isInstanceOf(Student.class);
        assertThat(actual.getBody().getId()).isEqualTo(targetId);
    }

    @Test
    void whenCreateNewStudent_thenResponseShouldBe200() {
        //Given
        Student expected = Student.builder()
                .id("123")
                .build();
        given(studentService.createStudent(expected)).willReturn(expected);
        //When
        ResponseEntity<Student> actual = controller.createNewStudent(expected);
        //Then
        HttpStatus expectedStatus = HttpStatus.OK;
        assertThat(actual.getStatusCode()).isEqualTo(expectedStatus);
        assertThat(actual.getBody()).isInstanceOf(Student.class);
        assertThat(actual.getBody().getId()).isEqualTo(expected.getId());
    }

    @Test
    void whenUpdateExistingStudent_thenResponseCodeShouldBe200() {
        //Given
        Student expected = Student.builder()
                .id("123")
                .build();
        given(studentService.updateStudent(expected)).willReturn(expected);
        //When
        ResponseEntity<Student> actual = controller.updateExistingStudent(expected);
        //Then
        HttpStatus expectedStatus = HttpStatus.OK;
        assertThat(actual.getStatusCode()).isEqualTo(expectedStatus);
        assertThat(actual.getBody()).isInstanceOf(Student.class);
        assertThat(actual.getBody().getId()).isEqualTo(expected.getId());
    }

    @Test
    void whenDeleteStudent_thenReturn200() {
        //Given
        String targetId = "123";
        given(studentService.deleteStudentById(targetId)).willReturn(targetId);
        //When
        ResponseEntity<String> actual = controller.deleteStudentById(targetId);
        //Then
        HttpStatus expectedStatus = HttpStatus.OK;
        assertThat(actual.getStatusCode()).isEqualTo(expectedStatus);
        assertThat(actual.getBody()).isInstanceOf(String.class);
        assertThat(actual.getBody()).isEqualTo(targetId);
    }
}