package org.example.engine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//commit is a file,we need to write in the file the sh1 of all the changes in this commit (tree or blob)
// to any branch we create a HEAD file that always changes to the last commit
public class gitCommit {
    private String treeRootHash; //sh-1 for the main tree
    private String parentCommitHash;
    private String secondParentCommitHash;
    private String comment;
    private String creationTime;
    private String author;
    private ArrayList<String> gitFileSh1;
    private String mySh1;
    private String nameOfRootDirectory;
    private boolean isFirst;
    private List<File> files;//files how change in this commit
    public gitCommit(String hashParent, String hashRootDirectory, String author, String comment,String path) {
        TimeHandler timeHandler = TimeHandler.getInstance();
        this.treeRootHash = hashRootDirectory;
        this.parentCommitHash = hashParent;
        this.secondParentCommitHash = null;
        this.comment = comment;
        //this.creationTime = LocalDate.now().toString();
        this.creationTime = timeHandler.getTime();
        this.author = author;
        saveToFile(path);
        gitFileSh1 = new ArrayList<>();
        this.nameOfRootDirectory = ".AGit";
        this.isFirst = true;
    }
    //create new commit and the head now point on this
    public void commit(String hashParent, String hashRootDirectory, String author, String comment,String path){
        //we need to create an array of all the tree and the blob how change in this running
        // we know how belong to this array by calculate the sha1 of all the file before the sha1( maybe we load it from file) and calculate the current sh1 and create the
        // ... zip blob and the file how represent a folder by the new sh1
        //create a table that hold all the sha1 that created in this program, this table will save in this project root
        TimeHandler timeHandler = TimeHandler.getInstance();
        this.treeRootHash = hashRootDirectory;
        this.parentCommitHash = hashParent;
        this.secondParentCommitHash = null; //need to write a function that get the second commit
        this.comment = comment;
        //this.creationTime = LocalDate.now().toString();
        this.creationTime = timeHandler.getTime();
        this.author = author;
        saveToFile(path);
        gitFileSh1 = new ArrayList<>();

        changeFileHowChangeInThisCommit(path);

    }
    public String getGitFileSh1(String sha1Name) {
        for (String sha1 : gitFileSh1) {
            if (sha1.equals(sha1Name)) {
                return sha1;
            }
        }
        System.err.println("SHA-1 not found for the given name: " + sha1Name);
        return null;
    }
    private void saveToFile(String repositoryPath) {
        sha256 sha = sha256.getInstance();
        String myContent = parentCommitHash+","+secondParentCommitHash+","
                +treeRootHash+","+comment+","+this.creationTime+","+author;

        String myHash = sha.getHash(myContent);
        this.mySh1 = myHash;
        changeHeadFileContent();
//        try (FileWriter writer = new FileWriter(repositoryPath+".AOGit\\.Object\\"+myHash)) {//check
//            writer.write(myContent);
//            System.out.println("Data has been written to the file.");
        try (FileWriter writer = new FileWriter("./.AGit/.Object/"+myHash)) {//check //todo change it to the path that get from the user
            writer.write(myContent);
            System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            System.err.println("Failed to open the file for writing.");
        }
    }
    public void changeHeadFileContent(){
        String fileName = "HEAD";
        String folderPath ="./.AGit/"; //todo change it to the path that get from the user
        String filePath = folderPath + File.separator + fileName;
        File file = new File(filePath);
        if(file.exists()){
         if (!file.delete()){
             System.err.println("Failed to delete the file.");
         }
        }
         FileHandler fh = FileHandler.getInstance();
         fh.writeToFile(filePath,this.mySh1);
    }
    public void changeFileHowChangeInThisCommit(String path){
        FileHandler fileHandler = FileHandler.getInstance();
        ArrayList<File> files =  fileHandler.collectFileInFolder(path);
        ArrayList<String> sh1AllFileInFiles = calculateSh1ForFileAndFolder(files);
        ArrayList<String> sh1OfFileThatNotExist = checkSh1ExistInDB(sh1AllFileInFiles);//if the sh1 notExist the file changes
        if (sh1OfFileThatNotExist == null){//nothing change in this commit
            return;
        }
        else {//write to DB the new Sh1 (of the new files)
            //need to write all the sh1's in text file
            fileHandler.writeToDBFile(sh1OfFileThatNotExist);
            ArrayList<File> newFiles = getFileBySh1(sh1OfFileThatNotExist,files);
            this.files = newFiles;
            //create the folder (tree) and the blob (need to be a recursion function )
            createTreeAndBlob(path);
        }
    }
    public void createTreeAndBlob(String path){
        //let assume that we have the list of all the file that changed
        //first create a file that represent the root tree and then init the String-sh1
        for (File file:this.files){
            searchRootDirectory(file);
            //create a tree
            String parentDirectoryPath = file.getParent();
        }
    }
    private void searchRootDirectory(File file){
        String parentDirectoryPath = file.getParent();
        if (parentDirectoryPath.equals(this.nameOfRootDirectory))
            return;
        else {
            searchRootDirectory(file.getParentFile());
            //need to create a tree
            FileHandler fileHandler = FileHandler.getInstance();
            if (this.isFirst){
                this.isFirst = false;
                fileHandler.createNewTreeFile(file.getParent());//the main root need to point on this file
            }
            else {
                //need to write in this file that represent a folder the format data of the content of the current folder
                //our parent are exist
                //create a new object that transfer the data that we want to write to the file
                fileHandler.writeToFileBuyName(file.getParent());//need to pass an object
            }
        }
    }
    public ArrayList<File> getFileBySh1(ArrayList<String> sh1OfFileThatNotExist, ArrayList<File> files){
        ArrayList<File> filteredFiles = new ArrayList<>();
        sha256 sha = sha256.getInstance();
        for (File file: files){
            String sh1 = sha.getHash(file);
            if (sh1OfFileThatNotExist.contains(sh1)){
                filteredFiles.add(file);
            }
        }
        if (filteredFiles.isEmpty())
            return null;
        else
            return filteredFiles;
    }
    public ArrayList<String> checkSh1ExistInDB(ArrayList<String> sh1AllFileInFiles){
        ArrayList<String> newSh1 = new ArrayList<>();
        for (String sh1: sh1AllFileInFiles){
            if (!existInDB(sh1)){
                newSh1.add(sh1);
            }
        }
        if (newSh1.isEmpty())
            return null;
        else
            return newSh1;
        // if the sh1 is not exist we need to check the file that represent this sh1 and check if it folder or file and create the correct information
    }
    public boolean existInDB(String sh1){// in this function we need to load all the data from the db and check if the sh1 are exist
        FileHandler fileHandler = FileHandler.getInstance();
        return fileHandler.doesStringExistInFile(sh1);
    }
    public ArrayList<String> calculateSh1ForFileAndFolder(  ArrayList<File> files ){
        sha256 sha = sha256.getInstance();
        ArrayList<String> sh1AllFileInFiles = new ArrayList<>();
        for (File file:files){
            sh1AllFileInFiles.add(sha.getHash(file));
        }
        if (sh1AllFileInFiles.isEmpty()){
            return null;
        }
        return sh1AllFileInFiles;
    }
    public void addGitFileSh1(String sha1) {
        gitFileSh1.add(sha1);
    }
    public String getHashParent() {
        return parentCommitHash;
    }
    public void setHashParent(String hashParent) {
        this.parentCommitHash = hashParent;
    }
    public String getHashRootDirectory() {
        return treeRootHash;
    }
    public void setHashRootDirectory(String hashRootDirectory) {
        this.treeRootHash = hashRootDirectory;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getMySh1() {
        return mySh1;
    }
    public void setMySh1(String mySh1) {
        this.mySh1 = mySh1;
    }

}
