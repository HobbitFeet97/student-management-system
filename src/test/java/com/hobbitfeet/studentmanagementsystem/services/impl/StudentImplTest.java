package com.hobbitfeet.studentmanagementsystem.services.impl;

import com.hobbitfeet.studentmanagementsystem.entities.Student;
import com.hobbitfeet.studentmanagementsystem.exceptions.EntityNotFoundException;
import com.hobbitfeet.studentmanagementsystem.exceptions.ImproperUpdateRequestException;
import com.hobbitfeet.studentmanagementsystem.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
class StudentImplTest {

    private StudentImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        studentService = new StudentImpl(studentRepository);
    }

    @Test
    void whenGetAllStudentsCalled_thenArrayIsReturned() {
        //Given
        given(studentRepository.findAll()).willReturn(
                List.of(Student.builder()
                        .id("123")
                        .firstName("Andrew")
                        .lastName("Thomas")
                        .isActive(true)
                        .build())
        );
        //When
        Iterable<Student> students = studentService.getAllStudents();
        //Then
        String expected = "123";
        assertThat(students.iterator().next().getId()).isEqualTo(expected);
    }

    @Test
    void whenGetStudentById_andStudentExists_returnStudent() throws EntityNotFoundException {
        //Given
        String searchId = "123";
        given(studentRepository.findById(searchId)).willReturn(Optional.of(
                Student.builder()
                        .id("123")
                        .isActive(true)
                        .build()
        ));
        //When
        Student actual = studentService.getStudentById(searchId);
        //Then
        assertThat(actual.getId()).isEqualTo(searchId);
    }

    @Test
    void whenGetStudentById_andStudentDoesNotExist_throwNotFound() {
        //Given
        String searchId = "123";
        given(studentRepository.findById(searchId)).willReturn(Optional.empty());
        //Then
        assertThatThrownBy(() -> {
            studentService.getStudentById(searchId);
        }).isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(searchId);
    }

    @Test
    void whenDeleteStudentById_andStudentExists_returnDeletedId() throws EntityNotFoundException {
        //Given
        String deleteId = "123";
        given(studentRepository.findById(deleteId)).willReturn(Optional.of(
                Student.builder()
                        .id("123")
                        .isActive(true)
                        .build()
        ));
        //When
        String actual = studentService.deleteStudentById(deleteId);
        //Then
        assertThat(actual).isEqualTo(deleteId);
    }

    @Test
    void whenDeleteStudentById_andStudentDoesNotExist_throwNotFound() {
        //Given
        String deleteId = "123";
        given(studentRepository.findById(deleteId)).willReturn(Optional.empty());
        //Then
        assertThatThrownBy(() -> {
            studentService.deleteStudentById(deleteId);
        }).isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(deleteId);
    }

    @Test
    void whenCreateStudent_returnedStudentHasIdAndCreatedOn() {
        //Given
        Student created = Student.builder()
                .isActive(true)
                .build();
        Student expected = Student.builder()
                .isActive(true)
                .id("123")
                .createdOn(LocalDateTime.now())
                .build();
        given(studentRepository.save(created)).willReturn(expected);
        //When
        Student actual = studentService.createStudent(created);
        //Then
        assertThat(actual.getId()).isNotEmpty();
        assertThat(actual.getCreatedOn()).isNotNull();
    }

    @Test
    void whenUpdateStudent_andStudentHasNoId_thenThrowsImproperUpdateRequest() {
        //Given
        Student updateStudent = Student.builder().build();
        //Then
        assertThatThrownBy(() -> {
            studentService.updateStudent(updateStudent);
        }).isInstanceOf(ImproperUpdateRequestException.class);
    }

    @Test
    void whenUpdateStudent_andStudentDoesNotExist_thenThrowsImproperUpdateRequest() {
        //Given
        Student updateStudent = Student.builder()
                .id("123")
                .build();
        //When
        given(studentRepository.findById(updateStudent.getId())).willReturn(Optional.empty());
        //Then
        assertThatThrownBy(() -> {
            studentService.updateStudent(updateStudent);
        }).isInstanceOf(ImproperUpdateRequestException.class)
                .hasMessageContaining(updateStudent.getId());
    }

    @Test
    void whenUpdateStudent_andStudentExists_thenReturnStudentWithUpdatedOn() throws ImproperUpdateRequestException {
        //Given
        LocalDateTime now = LocalDateTime.now();
        Student updateStudent = Student.builder()
                .id("123")
                .build();
        given(studentRepository.findById(updateStudent.getId())).willReturn(Optional.of(
                Student.builder()
                        .id("123")
                        .build()
        ));
        given(studentRepository.save(updateStudent)).willReturn(Student.builder()
                .updatedOn(now)
                .id("123")
                .build());
        //When
        Student actual = studentService.updateStudent(updateStudent);
        //Then
        assertThat(actual.getUpdatedOn()).isEqualTo(now.toString());
    }
}