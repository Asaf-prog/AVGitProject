package com.maven.test.avgitproject.daoUser;

import com.maven.test.avgitproject.entity.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;

@Lazy
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByPassword(String password);
}
