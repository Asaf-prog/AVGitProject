package com.maven.test.avgitproject.service;

import com.maven.test.avgitproject.daoUser.UserRepository;
import com.maven.test.avgitproject.entity.User;
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
}
