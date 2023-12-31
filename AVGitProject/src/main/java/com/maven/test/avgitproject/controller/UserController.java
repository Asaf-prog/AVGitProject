package com.maven.test.avgitproject.controller;

import com.maven.test.avgitproject.constants.Constants;
import com.maven.test.avgitproject.dto.*;
import com.maven.test.avgitproject.entity.Sh1Detail;
import com.maven.test.avgitproject.entity.User;
import com.maven.test.avgitproject.service.UserService;
import com.maven.test.avgitproject.utils.SessionUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.example.Exception.NoCommitException;
import org.example.Exception.PathNotFoundException;
import org.example.dto.CommitMappingDTO;
import org.example.dto.GitCommitDTO;
import org.example.dto.GitInitDTO;
import org.example.engine.Commit;
import org.example.engine.fileHandler.FileHandler;
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

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO dto, HttpServletRequest request) {
      // Search by password
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
        }else {// Need to register after authentication
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

            User userEntity = new User(dto.getFirstName(),
                    dto.getLastName(),
                    dto.getUserName(),
                    dto.getEmail(),
                    dto.getSh1(),
                    dto.getPassword());

            userEntity.setSh1(createSh1ForNewUser(userEntity));

            userService.save(userEntity);
            request.getSession(true).setAttribute(Constants.USER_ID, userEntity.getId());

            UserDTO userDTO = new UserDTO(userEntity.getFirstName(),
                    userEntity.getLastName(),
                    userEntity.getUserName(),
                    userEntity.getEmail(),
                    userEntity.getSh1(),
                    userEntity.getPassword());

            return ResponseEntity.status(200).body(userDTO);
        }else
            return ResponseEntity.status(500).body("The User Exist");
    }

    @PostMapping("/reposUser")
    public List<Sha1DetailDTO> getAllTheRepositoriesOfUser(@RequestBody UserLoginDTO dto){
        Convertor convertor = Convertor.getInstance();
        User user = userService.getSh1FromUser(dto);

        List<Sh1Detail> sh1Details = userService.findSh1DetailByUserId(user.getId());
        user.setSh1Details(sh1Details);
        List<Sha1DetailDTO> sha1DetailDTOS = convertor.convertSh1Details(user.getSh1Details());
        return sha1DetailDTOS;
    }

    @PostMapping("/getAllCommitRepo")
    public ResponseEntity<?> getAllCommitOfRepositoryByPath(@RequestBody CommitDTO commitDTO){
       try{
           // First we get the path and check if the path exist in db
           String path = userService.getPathFromUserAndRepo(commitDTO);
           if (path.equals(commitDTO.getPath())){
               FileHandler fileHandler = FileHandler.getInstance();
               fileHandler.setPath(path);
               // Need to return a list of Commit from the start to end
               List<CommitMappingDTO> commitList = userService.getListOfCommit(commitDTO.getPath());

               return ResponseEntity.status(200).body(commitList);
           }else {
               throw new PathNotFoundException("Path not found in the database: " + path);
           }
       } catch (PathNotFoundException e) {
           throw new RuntimeException(e);
       }catch (Exception e){
           System.err.println("An unexpected error occurred: " + e.getMessage());
           return null;
       }
    }

    @GetMapping("/getLastCommitRepo")
    public ResponseEntity<?> getLastCommitOfRepositoryByPath(@RequestBody CommitDTO commitDTO){
        try{
            // First we get the path and check if the path exist in db
            String path = userService.getPathFromUserAndRepo(commitDTO);
            if (path.equals(commitDTO.getPath())){
                FileHandler fileHandler = FileHandler.getInstance();
                fileHandler.setPath(path);

                Commit lastCommit = userService.getLastCommit(commitDTO.getPath());
                if (lastCommit != null){
                    return ResponseEntity.status(200).body(lastCommit);
                }else {
                    throw new NoCommitException("No commit found.");
                }
            }else {
                throw new PathNotFoundException("Path not found in the database: " + path);
            }
        } catch (PathNotFoundException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            System.err.println("An unexpected error occurred: " + e.getMessage());
            return null;
        }
    }

    private String createSh1ForNewUser(User userEntity) {
        return userService.createSh1ForNewUser(userEntity);
    }

    @PostMapping("/gitInit")
    public ResponseEntity<?> gitInit(@RequestBody GitInitDTO dto){
        if(userService.checkGitInit(dto)){
            userService.gitInit(dto);
            return ResponseEntity.status(200).body(dto);
        }else {
            return ResponseEntity.status(500).body("The Data Of The User Is Empty");
        }

    }

    @PostMapping("gitCommit")
    public ResponseEntity<?> gitCommit (@RequestBody GitCommitDTO dto) {

        if (userService.checkGitCommit(dto)){
            userService.gitCommit(dto);
            return ResponseEntity.status(200).body(dto);
        }else {
            return  ResponseEntity.status(500).body("The Data How Send To Git Init Are Not Complete");
        }

    }

    private boolean isValidName(String name) {
        // Check if the name contains only alphabetic characters
        return name.matches("^[a-zA-Z]{1,15}$");
    }

}
