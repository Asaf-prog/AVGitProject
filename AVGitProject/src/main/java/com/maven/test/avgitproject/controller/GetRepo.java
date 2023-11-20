package com.maven.test.avgitproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/AvGit")
public class GetRepo {
    @GetMapping("/Repo")
    public List<ServerResponse> getData(@RequestParam(value = "myName", defaultValue = "World") String name) {

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
}
