package org.example;

import org.example.engine.FileHandler;
import org.example.engine.git;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> stringList = new ArrayList<>();

        stringList.add("Apple");
        stringList.add("Banana");

        FileHandler fileHandler = FileHandler.getInstance();
        fileHandler.writeToDBFile(stringList);
        boolean check = fileHandler.doesStringExistInFile("Date");
        if (check)
            System.out.println("exist");

        fileHandler.collectFileInFolder("C:\\Users\\asafr\\OneDrive\\מסמכים\\GitHub\\AVGitProject\\engine\\src\\main\\java\\org\\example\\engine");
        String currentDirectory = System.getProperty("user.dir");

        System.out.println("Current working directory: " + currentDirectory);
        git gitObject = new git();
       //need to get from the user the first comment
       String comment = "comment on the first commit";

       // gitObject.gitInit("./","testRepoNew",comment);
        //gitObject.gitInit("C:\\Users\\asafr\\Desktop\\testforgit\\.Object\\test1","testRepoNew",comment);

        fileHandler.setPath("C:\\Users\\asafr\\Desktop\\testforgit");
        gitObject.gitInit("C:\\Users\\asafr\\Desktop\\testforgit","testRepoNew",comment);

        //fileHandler.testToZipFile();
    }
}