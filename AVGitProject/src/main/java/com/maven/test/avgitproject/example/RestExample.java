package com.maven.test.avgitproject.example;

import com.google.gson.Gson;
import com.maven.test.avgitproject.controller.MyRequestData;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Configuration
@RequestMapping("/AvGit")
public class RestExample {
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

}
