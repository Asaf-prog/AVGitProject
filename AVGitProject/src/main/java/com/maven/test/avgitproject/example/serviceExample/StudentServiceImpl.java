package com.maven.test.avgitproject.example.serviceExample;


import com.maven.test.avgitproject.example.daoStudent.StudentRepository;
import com.maven.test.avgitproject.example.entityExample.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

   private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student tempStudent) {
        return studentRepository.save(tempStudent);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int theId) {
        Optional<Student> result = studentRepository.findById(theId);
        Student student = null;
        if (result.isPresent()){
          student = result.get();
        }else {
            throw new RuntimeException("Did not find student id - " + theId);
        }
        return student;
    }
}
