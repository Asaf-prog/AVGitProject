package com.maven.test.avgitproject;

import com.maven.test.avgitproject.entity.Sh1Detail;
import com.maven.test.avgitproject.entity.User;
import com.maven.test.avgitproject.entity.UserDetail;
import com.maven.test.avgitproject.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.UUID;


@SpringBootApplication( scanBasePackages = "com.maven.test.avgitproject" )
@RestController
@Configuration
@RequestMapping("/AvGit")
public class AvGitProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvGitProjectApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/AvGit/**")
                        .allowedOrigins("http://localhost:5173") // Allow requests from this origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }

    @Bean
    public CommandLineRunner commandLineRunner () {
        return args -> {
            System.out.println("hello world!");
        };
    }


//    @Bean
//    public CommandLineRunner (UserServiceImpl userService) {//commandLineRunner
//        return args -> {
//            //  createUser( userService);
//            //createUserWithSh1Detail(userService);
//            //findUserWithSh1(userService);//without lazy annotation
//            // findSh1DetailsForUserById(userService);
//            System.out.println("hello world!");
//        };
//    }

    private void findSh1DetailsForUserById(UserServiceImpl userService) {
        int theId = 1;
        User user = userService.findById(theId);
        List<Sh1Detail> sh1Details = userService.findSh1DetailByUserId(theId);
        user.setSh1Details(sh1Details);
        System.out.println("result: " + user.getSh1Details());
        System.out.println("Done!");
    }

    private void findUserWithSh1(UserServiceImpl userService) {
        int theId = 1;
        User user = userService.findById(theId);
        System.out.println(user.getSh1Details());
    }

    private void createUserWithSh1Detail(UserServiceImpl userService) {
        System.out.println("create user...");

        UUID uuid = UUID.randomUUID();
        User tempUser = new User("avi","varon", "User Name","asafrefaelvaron1@gmail.com","12435",uuid.toString());
        UserDetail tempUserDetail = new UserDetail("youtubeChannel","soccer game");
        tempUser.setUserDetail(tempUserDetail);

        //Sh1Detail sh1Detail1 = new Sh1Detail("1223","firstRepoName","./example");
        //Sh1Detail sh1Detail2 = new Sh1Detail("122","2RepoName","./example");
        //Sh1Detail sh1Detail3 = new Sh1Detail("1222","3RepoName","./example");
        //tempUser.add(sh1Detail1);
        //tempUser.add(sh1Detail2);
        //tempUser.add(sh1Detail3);

        System.out.println("Saving user" + tempUser);
        System.out.println("The sh1repo"+ tempUser.getSh1Details());
        userService.save(tempUser);

        System.out.println("Done!");
    }

    private void createUser(UserServiceImpl userService) {
        System.out.println("create user...");

        UUID uuid = UUID.randomUUID();
        User tempUser = new User("avi","varon", "User Name","asafrefaelvaron1@gmail.com","12435",uuid.toString());

        userService.save(tempUser);

        System.out.println("save user generate id: " + tempUser.getId());
    }

}
