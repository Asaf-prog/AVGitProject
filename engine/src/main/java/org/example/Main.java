package org.example;

import org.example.engine.FileHandler;
import org.example.engine.git;
import org.example.engine.gitCommit;

public class Main {
    public static void main(String[] args) {

        git gitObject = new git();
       //need to get from the user the first comment
       String comment = "comment on the first commit";

        FileHandler fileHandler = FileHandler.getInstance();

        //get path from the user
        fileHandler.setPath("C:\\Users\\asafr\\Desktop\\testforgit");
        gitObject.gitInit("C:\\Users\\asafr\\Desktop\\testforgit","testRepoNew",comment);

        //create a commit on current repo (we already do git init)
        String pathOfNewCommit ="C:\\Users\\asafr\\Desktop\\testforgit";//this path we get from the user
        System.out.println(fileHandler.getSh1HeadFile(pathOfNewCommit + "\\.AGit\\Head"));
    }
}