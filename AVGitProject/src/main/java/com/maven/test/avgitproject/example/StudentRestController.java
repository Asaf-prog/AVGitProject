package com.maven.test.avgitproject.example;

import com.maven.test.avgitproject.example.entityExample.Student;
import com.maven.test.avgitproject.example.serviceExample.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/studentAdd")
    public Student addStudent (@RequestBody Student theStudent) {
        theStudent.setId(0);

        Student dbStudent = studentService.save(theStudent);
        return dbStudent;
    }

}
