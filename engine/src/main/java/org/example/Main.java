package org.example;

import org.example.display.DisplayData;
import org.example.engine.FileHandler;
import org.example.engine.git;
import org.example.engine.gitCommit;

public class Main {
    public static void main(String[] args) {

        git gitObject = new git();
       // Need to get from the user the first comment
       String comment = "comment on the first commit";

       FileHandler fileHandler = FileHandler.getInstance();

        //get path from the user
        fileHandler.setPath("C:\\Users\\asafr\\Desktop\\testforgit");
        gitObject.gitInit("C:\\Users\\asafr\\Desktop\\testforgit","The name ",comment);


        DisplayData displayData = new DisplayData("C:\\Users\\asafr\\Desktop\\testforgit");
        displayData.showAllCommitByPath();

        String hashParent = fileHandler.extractStringOfLastCommitBetweenCommas("./gitRepos");
        String commitPath = "C:\\Users\\asafr\\Desktop\\testforgit";
        String repoName = "repoNameGetFromUser";
        gitCommit commit = new gitCommit(hashParent,null,"asaf","commit without init",commitPath,repoName);

    }
}