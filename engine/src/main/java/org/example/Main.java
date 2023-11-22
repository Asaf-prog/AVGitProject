package org.example;

import org.example.display.DisplayData;
import org.example.dto.GitInitDTO;
import org.example.engine.FileHandler;
import org.example.engine.git;
import org.example.engine.gitCommit;
import org.example.engine.sha256;
import org.example.execution.ExecutionManager;
import org.example.execution.ExecutionTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) {

        git gitObject = new git();
       // Need to get from the user the first comment
       String comment = "comment on the first commit";

       FileHandler fileHandler = FileHandler.getInstance();



        //get path from the user
        fileHandler.setPath("C:\\Users\\asafr\\Desktop\\testforgit");
       gitObject.gitInit("C:\\Users\\asafr\\Desktop\\testforgit","The name ",comment);

//        GitInitDTO gitInitDTO = new GitInitDTO("C:\\Users\\asafr\\Desktop\\testforgit","The name ",comment);
//        ExecutionManager executionManager = ExecutionManager.getInstance();
//        ExecutionTask task = new ExecutionTask(gitInitDTO);
//
//        Future<?> future = executionManager.submitTask(task);
//
//      //  executionManager.executeTask(task);
//        // ////////////////////////-Create a new commit-///////////////////////
//
//        try {
//            future.get();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }

        String hashParent = fileHandler.extractStringOfLastCommitBetweenCommas("./gitRepos");
        String commitPath = "C:\\Users\\asafr\\Desktop\\testforgit";
        String repoName = "repoNameGetFromUser";
        gitCommit commit = new gitCommit(hashParent,null,"asaf","commit without init",commitPath,repoName);

    }
}