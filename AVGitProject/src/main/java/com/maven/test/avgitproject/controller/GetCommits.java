package com.maven.test.avgitproject.controller;

import com.maven.test.avgitproject.dto.CommitDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/AvGit")
public class GetCommits {
    @GetMapping("/commit")
    public List<CommitDTO> getCommit(@RequestParam(value = "myName", defaultValue = "default") String name) {

        List<CommitDTO> commitList = new ArrayList<>();

        // Populate the list with Commit objects
        commitList.add(new CommitDTO("sha1_1", "repo 1", "2023-01-01", "John Doe"));
        commitList.add(new CommitDTO("sha1_2", "repo 2", "2023-01-02", "Jane Smith"));
        commitList.add(new CommitDTO("sha1_3", "repo 3", "2023-01-03", "Michael Johnson"));
        return commitList;
    }

    @GetMapping("/repo")
    public List<CommitDTO> getRepo(@RequestParam(value = "myName", defaultValue = "default") String name) {

        List<CommitDTO> commitList = new ArrayList<>();

        // Populate the list with Commit objects
        commitList.add(new CommitDTO("54", "Commit 1", "2023-01-01", "John Doe"));
        commitList.add(new CommitDTO("55", "Commit 2", "2023-01-02", "Jane Smith"));
        commitList.add(new CommitDTO("56", "Commit 3", "2023-01-03", "Michael Johnson"));
        return commitList;
    }

}

