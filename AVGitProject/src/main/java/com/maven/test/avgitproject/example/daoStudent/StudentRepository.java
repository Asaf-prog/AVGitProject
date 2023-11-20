package com.maven.test.avgitproject.example.daoStudent;

import com.maven.test.avgitproject.example.entityExample.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository <Student , Integer> {
    // that's it... no need to write any code !
}
