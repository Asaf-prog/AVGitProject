package com.maven.test.avgitproject.daoUser;

import com.maven.test.avgitproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    // that's it... no need to write any code !
    User findByPassword(String password);
}