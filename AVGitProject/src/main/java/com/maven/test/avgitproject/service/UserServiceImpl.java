package com.maven.test.avgitproject.service;

import com.maven.test.avgitproject.daoUser.Sh1Repository;
import com.maven.test.avgitproject.daoUser.UserRepository;
import com.maven.test.avgitproject.dto.CommitDTO;
import com.maven.test.avgitproject.dto.UserLoginDTO;
import com.maven.test.avgitproject.entity.Sh1Detail;
import com.maven.test.avgitproject.entity.User;
import org.example.display.DisplayData;
import org.example.dto.GitCommitDTO;
import org.example.dto.GitInitDTO;
import org.example.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private Sh1Repository sh1Repository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User tempUser) {
        return userRepository.save(tempUser);
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
        User user = userRepository.findByPassword(dto.getUserPassword());
        Sh1Detail sh1Detail = new Sh1Detail(createSh1ForNewUser(user), dto.getRepoName(), dto.getPath());

        Git gitObject = new Git();
        FileHandler fileHandler = FileHandler.getInstance();
        fileHandler.setPath(dto.getPath());
        gitObject.gitInit(dto.getPath(), dto.getRepoName(), dto.getComment());

        user.add(sh1Detail);
        // Save the updated User to the database
        userRepository.save(user);

    }

    @Override
    public void gitCommit(GitCommitDTO dto) {
        // Here I get the path of the repository
        String path = getCurrentHashParent(dto);
        FileHandler fileHandler = FileHandler.getInstance();
        fileHandler.setPath(path);
        String hashParent = fileHandler.getSh1OfLastCommit(path);
        dto.setHashParent(hashParent);
        GitCommit commit =
                new GitCommit(dto.getHashParent(), dto.getHasRootDirectory(),
                        dto.getAuthor(), dto.getComment(), dto.getPath(), dto.getRepoName());
    }

    private String getCurrentHashParent(GitCommitDTO dto) {
        User user = userRepository.findByPassword(dto.getPassword());
        List<Sh1Detail> sh1Details = userRepository.findSh1ListById(user.getId());

      Optional<Sh1Detail> firstPath =  sh1Details.stream()
                .filter(sh1Detail -> sh1Detail.getPath().equals(dto.getPath()))
                .findFirst();
      String path = null;
      if (firstPath.isPresent()){
          Sh1Detail sh1Detail = firstPath.get();
          path = sh1Detail.getPath();
      }
     return path;
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
    public List<Sh1Detail> findSh1DetailByUserId(int theId) {

        List<Sh1Detail> sh1Details = userRepository.findSh1ListById(theId);
        return sh1Details;
    }

    @Override
    public String getPathFromUserAndRepo(CommitDTO commitDTO) {
        User user = userRepository.findByPassword(commitDTO.getUserPassword());
        List<Sh1Detail> sh1Details = getSh1DetailByNameOfRepo(user);
        return getSh1DetailsWithCorrectNameOfRepo(sh1Details,commitDTO);

    }

    @Override
    public List<Commit> getListOfCommit(String path) {
        DisplayData displayData = new DisplayData(path);
        displayData.showAllCommitByPath();

        return displayData.getCommitsMapping();
    }

    @Override
    public Commit getLastCommit(String path) {
        DisplayData displayData = new DisplayData(path);
        displayData.showAllCommitByPath();
        List<Commit> commits = displayData.getCommitsMapping();
        Commit lastCommit = null;
        if (!commits.isEmpty()){
            lastCommit =  commits.get(commits.size() - 1);
        }
        return lastCommit;
    }

    private String getSh1DetailsWithCorrectNameOfRepo(List<Sh1Detail> sh1Details, CommitDTO commitDTO) {
        String path = null;
        Optional<Sh1Detail> sh1Detail = sh1Details.stream()
                .filter(sh1Detail1 -> sh1Detail1.getName().trim().equals(commitDTO.getNameOfRepo().trim()) )
                .findFirst();
        if (sh1Detail.isPresent()){
            Sh1Detail tempSh1 = sh1Detail.get();
            path = tempSh1.getPath();
        }
        return path;
    }


    private List<Sh1Detail> getSh1DetailByNameOfRepo(User user) {
        List<Sh1Detail> sh1Details = userRepository.findSh1ListById(user.getId());
        return sh1Details;
    }

}
