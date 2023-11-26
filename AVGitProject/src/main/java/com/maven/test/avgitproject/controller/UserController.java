package com.maven.test.avgitproject.controller;

import com.maven.test.avgitproject.constants.Constants;
import com.maven.test.avgitproject.dto.UserDTO;
import com.maven.test.avgitproject.dto.UserLoginDTO;
import com.maven.test.avgitproject.entity.User;
import com.maven.test.avgitproject.service.UserService;
import com.maven.test.avgitproject.utils.SessionUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.example.dto.GitCommitDTO;
import org.example.dto.GitInitDTO;
import org.example.engine.Git;
import org.example.engine.GitCommit;
import org.example.engine.Sha256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/AvGit")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAll(){
        return userService.findAll();
    }
    @PostMapping("/userAdd")
    public User addStudent (@RequestBody User theUser) {
        theUser.setId(0);

        User dbUser = userService.save(theUser);
        return dbUser;
    }

    @GetMapping("/user/{userId}")
    public User getUser (@PathVariable String userId) {
        User dbUser =userService.findByPassword(userId);
        return dbUser;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO dto, HttpServletRequest request) {
      //we search by password
        User userEntity = userService.findByPassword(dto.getPassword());
        UserLoginDTO userDTO = null;
        if (userEntity != null){//use exist
             userDTO = new UserLoginDTO( dto.getUserName(), dto.getPassword());
            String userIdFromSession = SessionUtils.getUserId(request);
            if (userIdFromSession == null) {
                request.getSession(true).setAttribute(Constants.USER_ID, userEntity.getId());
            }else {
                if (!userIdFromSession.equals(userEntity.getId())) {
                    SessionUtils.clearSession(request);
                    request.getSession(true).setAttribute(Constants.USER_ID, userEntity.getId());
                }
            }
            return ResponseEntity.status(200).body(userDTO);
        }else {//Need to register after authentication
            userDTO = dto;

            URI registerUri = UriComponentsBuilder.fromUriString(request.getRequestURL().toString())
                    .replacePath("/register")
                    .build()
                    .toUri();

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(registerUri);

            return ResponseEntity.status(201).headers(headers).body(userDTO);
        }
    }
    @PutMapping("/register")
    public ResponseEntity<?> register(HttpServletRequest request,@RequestBody UserDTO dto){
        if(!isValidName(dto.getFirstName()))
            return ResponseEntity.status(400).body("Invalid first name");
        if(!isValidName(dto.getLastName()))
            return ResponseEntity.status(406).body("Invalid last name");

        if(userService.findByPassword(dto.getPassword()) == null) {
            // Need to create a new Sh1 for the user
            User userEntity = new User(dto.getFirstName(), dto.getLastName(), dto.getUserName(), dto.getEmail(), dto.getSh1(), dto.getPassword());
            userEntity.setSh1(createSh1ForNewUser(userEntity));
            // Need to create a Folder to new User with the name of the sh1
            userService.save(userEntity);
            request.getSession(true).setAttribute(Constants.USER_ID, userEntity.getId());

            UserDTO userDTO = new UserDTO(userEntity.getFirstName(), userEntity.getLastName(), userEntity.getUserName(),userEntity.getEmail(),
                    userEntity.getSh1(), userEntity.getPassword());

            return ResponseEntity.status(200).body(userDTO);
        }else
            return ResponseEntity.status(500).body("Error creating exist account");
    }

    private String createSh1ForNewUser(User userEntity) {
        //The Sh1 Of The user  composed of all the data members without the sh1 member
        String content = userEntity.getFirstName() + userEntity.getLastName() + userEntity.getUserName() + userEntity.getEmail()+
                userEntity.getPassword();
        Sha256 sha = Sha256.getInstance();
        String sh1 = sha.getHash(content);
        return sh1;
    }

    @PostMapping("/gitInit")
    public ResponseEntity<?> gitInit(@RequestBody GitInitDTO dto){
        Git gitObject = new Git();
        gitObject.gitInit(dto.getPath(), dto.getRepoName(), dto.getComment());

        return ResponseEntity.status(200).build();
    }
    @PostMapping("gitCommit")
    public ResponseEntity<?> gitCommit (@RequestBody GitCommitDTO dto) {
        GitCommit commit =
                new GitCommit(dto.getHashParent(), dto.getHasRootDirectory(),
                        dto.getAuthor(), dto.getComment(), dto.getPath(), dto.getRepoName());

        return ResponseEntity.status(200).build();
    }
    private boolean isValidName(String name) {
        // Check if the name contains only alphabetic characters
        return name.matches("^[a-zA-Z]{1,15}$");
    }
}
