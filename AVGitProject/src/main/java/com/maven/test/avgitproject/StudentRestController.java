package com.maven.test.avgitproject;

import com.maven.test.avgitproject.entity.Student;
import com.maven.test.avgitproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/AvGit")
public class StudentRestController {

    private StudentService studentService;
    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/student")
    public List<Student> findAll(){
        return studentService.findAll();
    }
}
