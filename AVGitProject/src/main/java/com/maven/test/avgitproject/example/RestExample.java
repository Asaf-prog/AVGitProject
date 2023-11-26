package com.maven.test.avgitproject.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

@RestController
@Configuration
@RequestMapping("/AvGit")
public class RestExample {
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello friends %s!", name);
    }
}
