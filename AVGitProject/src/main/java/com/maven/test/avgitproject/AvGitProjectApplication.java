package com.maven.test.avgitproject;

import com.google.gson.Gson;
import org.example.Foo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AvGitProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvGitProjectApplication.class, args);
        Foo f = new Foo(1,"asaf");
        Gson gson = new Gson();
    }
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
