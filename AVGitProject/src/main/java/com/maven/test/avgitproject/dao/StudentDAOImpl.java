package com.maven.test.avgitproject.dao;

import com.maven.test.avgitproject.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    private EntityManager entityManager;

   @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
       entityManager.persist(theStudent);
    }

    @Override
    public List<Student> findAll() {
        // Create a query
        TypedQuery <Student> theQuery = entityManager.createQuery("from Student",Student.class);
        // execute the query and get the result list
        List <Student> students = theQuery.getResultList();

        return students;
    }
}
