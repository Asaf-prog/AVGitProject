package com.maven.test.avgitproject.service;

import com.maven.test.avgitproject.daoUser.UserRepository;
import com.maven.test.avgitproject.dto.UserLoginDTO;
import com.maven.test.avgitproject.entity.User;
import org.example.dto.GitCommitDTO;
import org.example.dto.GitInitDTO;
import org.example.engine.FileHandler;
import org.example.engine.Git;
import org.example.engine.GitCommit;
import org.example.engine.Sha256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User tempStudent) {
        return userRepository.save(tempStudent);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int theId) {
        Optional<User> result =userRepository.findById(theId);
        User user = null;
        if (result.isPresent()){
            user = result.get();
        }
        else {
            throw new RuntimeException("Did not find User id - " + theId);
        }
        return user;
    }

    @Override
    public User findByPassword(String password) {
        return userRepository.findByPassword(password);
    }

    @Override
    public void gitInit(GitInitDTO dto) {
        Git gitObject = new Git();
        gitObject.gitInit(dto.getPath(), dto.getRepoName(), dto.getComment());
    }

    @Override
    public void gitCommit(GitCommitDTO dto) {
        GitCommit commit =
                new GitCommit(dto.getHashParent(), dto.getHasRootDirectory(),
                        dto.getAuthor(), dto.getComment(), dto.getPath(), dto.getRepoName());
    }

    @Override
    public String createSh1ForNewUser(User userEntity) {
        //The Sh1 Of The user  composed of all the data members without the sh1 member
        String content = userEntity.getFirstName() + userEntity.getLastName() + userEntity.getUserName() + userEntity.getEmail()+
                userEntity.getPassword();
        Sha256 sha = Sha256.getInstance();
        String sh1 = sha.getHash(content);
        return sh1;
    }

    @Override
    public User getSh1FromUser(UserLoginDTO user) {
        return userRepository.findByPassword(user.getPassword());
    }

    @Override
    public List<String> getListOfRepoBySh1(String sh1) {
        FileHandler fileHandler = FileHandler.getInstance();
        return fileHandler.getListOfRepoNameBySh1(sh1);
    }

}
