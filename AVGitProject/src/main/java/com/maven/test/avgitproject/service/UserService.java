package com.maven.test.avgitproject.service;

import com.maven.test.avgitproject.entity.User;
import java.util.List;

public interface UserService {
    User save(User tempStudent);
    List<User> findAll();
    User findById (int theId);
    User findByPassword (String password);
}
