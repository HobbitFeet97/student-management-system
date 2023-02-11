package com.hobbitfeet.studentmanagementsystem.repositories;

import com.hobbitfeet.studentmanagementsystem.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
}
