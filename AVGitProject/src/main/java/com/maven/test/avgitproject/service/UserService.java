package com.maven.test.avgitproject.service;

import com.maven.test.avgitproject.dto.UserLoginDTO;
import com.maven.test.avgitproject.entity.User;
import org.example.dto.GitCommitDTO;
import org.example.dto.GitInitDTO;

import java.util.List;

public interface UserService {
    User save(User tempStudent);
    List<User> findAll();
    User findById (int theId);
    User findByPassword (String password);
    void gitInit(GitInitDTO dto);
    void gitCommit(GitCommitDTO dto);
    String createSh1ForNewUser(User userEntity);
    User getSh1FromUser(UserLoginDTO user);
    List<String> getListOfRepoBySh1(String sh1);
}
