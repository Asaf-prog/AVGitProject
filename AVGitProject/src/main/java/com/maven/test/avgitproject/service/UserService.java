package com.maven.test.avgitproject.service;

import com.maven.test.avgitproject.dto.CommitDTO;
import com.maven.test.avgitproject.dto.UserLoginDTO;
import com.maven.test.avgitproject.entity.Sh1Detail;
import com.maven.test.avgitproject.entity.User;
import org.example.dto.CommitMappingDTO;
import org.example.dto.GitCommitDTO;
import org.example.dto.GitInitDTO;
import org.example.engine.Commit;

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
    List<Sh1Detail> findSh1DetailByUserId(int theId);
    String getPathFromUserAndRepo(CommitDTO commitDTO);
    List<CommitMappingDTO> getListOfCommit(String path);
    Commit getLastCommit(String path);
}
