package org.example;

import org.example.display.DisplayData;
import org.example.engine.FileHandler;
import org.example.engine.Git;
import org.example.engine.GitCommit;

public class Main {
    public static void main(String[] args) {

        Git gitObject = new Git();
       // Need to get from the user the first comment
       String comment = "comment on the first commit";

       FileHandler fileHandler = FileHandler.getInstance();
       String reader = fileHandler.getSh1OfLastCommit("C:\\Users\\asafr\\Desktop\\testforgit");
        System.out.println("reader: " + reader);

        //get path from the user
        fileHandler.setPath("C:\\Users\\asafr\\Desktop\\testforgit");
        gitObject.gitInit("C:\\Users\\asafr\\Desktop\\testforgit","The name ",comment);


        DisplayData displayData = new DisplayData("C:\\Users\\asafr\\Desktop\\testforgit");
        displayData.showAllCommitByPath();

        String hashParent = fileHandler.extractStringOfLastCommitBetweenCommas("./gitRepos");
        String commitPath = "C:\\Users\\asafr\\Desktop\\testforgit";
        String repoName = "repoNameGetFromUser";
        GitCommit commit = new GitCommit(hashParent,null,"asaf","commit without init",commitPath,repoName);

    }
}