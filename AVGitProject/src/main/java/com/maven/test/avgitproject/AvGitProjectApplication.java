package com.maven.test.avgitproject;

import com.google.gson.Gson;
import com.maven.test.avgitproject.DTO.CommitDTO;
import org.example.Foo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class AvGitProjectApplication {

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

    public static void main(String[] args) {
        SpringApplication.run(AvGitProjectApplication.class, args);
        Foo f = new Foo(1,"asaf");
        Gson gson = new Gson();
    }
    @GetMapping("/data")
    public  List<ServerResponse>  getData(@RequestParam(value = "myName", defaultValue = "World") String name) {

        List<ServerResponse> serverResponses = new ArrayList<>();
        serverResponses.add(new ServerResponse("John Doe", 0));
        serverResponses.add(new ServerResponse("Jane Smith", 1));
        serverResponses.add(new ServerResponse("Michael Johnson", 2));
        serverResponses.add(new ServerResponse("Sarah Brown", 3));
        serverResponses.add(new ServerResponse("Sarah Brown", 4));
        serverResponses.add(new ServerResponse("Sarah Brown", 5));
        serverResponses.add(new ServerResponse("Sarah Brown", 6));
        serverResponses.add(new ServerResponse("Sarah Brown", 7));
        serverResponses.add(new ServerResponse("Sarah Brown", 8));
        serverResponses.add(new ServerResponse("asaf var", 9));
        serverResponses.add(new ServerResponse("David Lee", 10));

        return serverResponses;
    }
    @GetMapping("/commit")
    public  List<CommitDTO>  getCommit(@RequestParam(value = "myName", defaultValue = "default") String name) {
        List<CommitDTO> commitList = new ArrayList<>();

        // Populate the list with Commit objects
        commitList.add(new CommitDTO("sha1_1", "Commit 1", "2023-01-01", "John Doe"));
        commitList.add(new CommitDTO("sha1_2", "Commit 2", "2023-01-02", "Jane Smith"));
        commitList.add(new CommitDTO("sha1_3", "Commit 3", "2023-01-03", "Michael Johnson"));
        return commitList;
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

        // Process the data and return a response
        return "Received POST data: key1=" + key1 + ", key2=" + key2;
    }
}
