package com.maven.test.avgitproject.example.service;

import com.maven.test.avgitproject.example.entityExample.Student;

import java.util.List;

public interface StudentService {

    Student save(Student tempStudent);
    List<Student> findAll();
    Student findById (int theId);
}
