package com.maven.test.avgitproject.daoUser;


import com.maven.test.avgitproject.entity.Sh1Detail;
import com.maven.test.avgitproject.entity.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Lazy
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u.sh1Details FROM User u WHERE u.id = :userId")
    List<Sh1Detail> findSh1ListById(@Param("userId") int userId);
    User findByPassword(String password);
}
