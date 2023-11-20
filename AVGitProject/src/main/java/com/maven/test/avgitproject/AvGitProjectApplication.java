package com.maven.test.avgitproject;

import com.google.gson.Gson;
import com.maven.test.avgitproject.dao.StudentDAO;
import com.maven.test.avgitproject.entity.Student;
import org.example.Foo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
@Configuration
@RequestMapping("/AvGit")
public class AvGitProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvGitProjectApplication.class, args);
        Foo f = new Foo(1,"asaf");
        Gson gson = new Gson();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5174")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .maxAge(3600); // Max age of the CORS pre-flight request;
            }
        };
    }
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello friends %s!", name);
    }
    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String handlePostRequest(@RequestBody String json) {
        Gson gson = new Gson();
        MyRequestData requestData = gson.fromJson(json, MyRequestData.class);

        // You can now access the requestData object, which contains the deserialized JSON request body
        String key1 = requestData.getKey1();
        String key2 = requestData.getKey2();

        return "Received POST data: key1=" + key1 + ", key2=" + key2;
    }
    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {

        return args -> {

            createStudent(studentDAO);
        };
    }


    private void createStudent(StudentDAO studentDAO) {
        System.out.println("create student... ");

        Student tempStudent = new Student("asaf", "varon", "asafrefaelvaron1@gmail.com");


        System.out.println("save student");

        studentDAO.save(tempStudent);

        System.out.println("save student generate id: " + tempStudent.getId());
    }
}
