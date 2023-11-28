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
        commitList.add(new CommitDTO("sha1_4", "repo 4", "2023-01-03", "Michael Johnson"));
        commitList.add(new CommitDTO("sha1_5", "repo 5", "2023-01-03", "Michael Johnson"));
        commitList.add(new CommitDTO("sha1_6", "repo 6", "2023-01-03", "Michael Johnson"));
        commitList.add(new CommitDTO("sha1_7", "repo 7", "2023-01-03", "Michael Johnson"));
        commitList.add(new CommitDTO("sha1_8", "repo 8", "2023-01-03", "Michael Johnson"));
        return commitList;
    }

    @GetMapping("/repo")
    public List<CommitDTO> getRepo(@RequestParam(value = "myName", defaultValue = "default") String name) {

        List<CommitDTO> commitList = new ArrayList<>();

        // Populate the list with Commit objects
        commitList.add(new CommitDTO("54", "repo 1", "2023-01-01", "John Doe"));
        commitList.add(new CommitDTO("55", "repo 2", "2023-01-02", "Jane Smith"));
        commitList.add(new CommitDTO("56", "repo 3", "2023-01-03", "Michael Johnson"));
        commitList.add(new CommitDTO("57", "repo 4", "2023-01-03", "Michael Johnson"));
        commitList.add(new CommitDTO("58", "repo 5", "2023-01-03", "Michael Johnson"));
        commitList.add(new CommitDTO("59", "repo 6", "2023-01-03", "Michael Johnson"));
        return commitList;
    }

}

