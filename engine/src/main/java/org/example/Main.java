package org.example;

import org.example.engine.FileHandler;
import org.example.engine.git;
import org.example.engine.gitCommit;
import org.example.engine.sha256;

public class Main {
    public static void main(String[] args) {

        git gitObject = new git();
        sha256 sha = sha256.getInstance();
       // Need to get from the user the first comment
       String comment = "comment on the first commit";

       String bdika = sha.getHash("C:\\Users\\asafr\\Desktop\\tetsttt");
        System.out.println(bdika);
        FileHandler fileHandler = FileHandler.getInstance();

        //get path from the user
        fileHandler.setPath("C:\\Users\\asafr\\Desktop\\testforgit");
        gitObject.gitInit("C:\\Users\\asafr\\Desktop\\testforgit","The name ",comment);

        ////////////////////////////-Create a new commit-///////////////////////
        // Create a commit on current repo (we already do git init)
        String pathOfNewCommit ="C:\\Users\\asafr\\Desktop\\testforgit";// This path we get from the user
        System.out.println(fileHandler.getSh1HeadFile(pathOfNewCommit + "\\.AGit\\Head"));// Check
        // Now we get from the user the path of the current location
        //we get from the server the command of do a commit, and we get the path from the user
        //we get the repository name from the user => this is give us the ability to know where is the last commit

        String hashParent = fileHandler.extractStringOfLastCommitBetweenCommas("./gitRepos");
        System.out.println(hashParent);
        String commitPath = "C:\\Users\\asafr\\Desktop\\testforgit";
        String repoName = "repoNameGetFromUser";
        gitCommit commit = new gitCommit(hashParent,null,"asaf","commit without init",commitPath,repoName);

    }
}