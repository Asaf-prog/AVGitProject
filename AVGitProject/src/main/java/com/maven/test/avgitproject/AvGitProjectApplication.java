package com.maven.test.avgitproject;

import com.google.gson.Gson;
import org.example.Foo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
