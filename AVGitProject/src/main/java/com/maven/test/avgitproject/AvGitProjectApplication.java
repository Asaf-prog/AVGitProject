package com.maven.test.avgitproject;

import com.maven.test.avgitproject.entity.User;
import com.maven.test.avgitproject.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.UUID;

@SpringBootApplication
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
    public CommandLineRunner commandLineRunner (UserServiceImpl userService) {
        return args -> {
            createUser( userService);
        };
    }

    private void createUser(UserServiceImpl userService) {
        System.out.println("create user...");

        UUID uuid = UUID.randomUUID();
        User tempUser = new User("avi","varon", "asafrefaelvaron1@gmail.com","12435",uuid.toString());

        System.out.println("save User");
        userService.save(tempUser);

        System.out.println("save user generate id: " + tempUser.getId());
    }

}
