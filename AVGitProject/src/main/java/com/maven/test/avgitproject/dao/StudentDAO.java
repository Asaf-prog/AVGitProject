package com.maven.test.avgitproject.dao;

import com.maven.test.avgitproject.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save (Student theStudent);
    List<Student> findAll();
}
