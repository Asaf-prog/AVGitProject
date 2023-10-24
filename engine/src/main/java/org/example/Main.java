package org.example;

import org.example.engine.FileHandler;
import org.example.engine.git;
import org.example.engine.sha256;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> stringList = new ArrayList<>();

        // Add some values to the ArrayList
        stringList.add("Apple");
        stringList.add("Banana");
        stringList.add("Cherry");
        stringList.add("Date");
        stringList.add("Fig");

        sha256 sha = sha256.getInstance();
        FileHandler fileHandler = FileHandler.getInstance();
        fileHandler.writeToDBFile(stringList);
        boolean check = fileHandler.doesStringExistInFile("Date");
        if (check)
            System.out.println("exist");
        String input = "Hello, world!";
        String hash = sha.getHash(input);
        System.out.println("SHA-256 hash: " + hash);


        // C:\Users\asafr\OneDrive\מסמכים\GitHub\AGit\src\main\java\com\maven\test\engine//last version
        //C:\Users\asafr\OneDrive\מסמכים\GitHub\AVGitProject\engine\src\main\java\org\example\engine
        fileHandler.collectFileInFolder("C:\\Users\\asafr\\OneDrive\\מסמכים\\GitHub\\AVGitProject\\engine\\src\\main\\java\\org\\example\\engine");
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentDirectory);
        git gitObject = new git();
        gitObject.gitInit("./","testRepoNew");


        //fileHandler.testToZipFile();

    }
}