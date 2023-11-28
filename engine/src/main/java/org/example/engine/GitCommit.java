package org.example.engine;

import org.example.Exception.UnknownEntityTypeException;
import org.example.Exception.ZipFileCreationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GitCommit {
    private String treeRootHash; // Sh1 for the main tree
    private String parentCommitHash;
    private String secondParentCommitHash;
    private String comment;
    private String creationTime;
    private String author;
    private ArrayList<String> gitFileSh1;
    private String mySh1;
    private String nameOfRootDirectory;
    private String lastCommit;
    private boolean isFirst;
    private String repoName;
    private List<File> files;// Files how changed in this commit

    public GitCommit(String hashParent, String hashRootDirectory, String author, String comment, String path, String repoName) {
        TimeHandler timeHandler = TimeHandler.getInstance();
        this.treeRootHash = hashRootDirectory;
        this.parentCommitHash = hashParent;
        this.secondParentCommitHash = null;
        this.comment = comment;
        this.creationTime = timeHandler.getTime();
        this.author = author;
        this.repoName = repoName;
        gitFileSh1 = new ArrayList<>();
        this.nameOfRootDirectory = path;
        this.isFirst = true;
        commit(hashParent,hashRootDirectory,author,comment,path);
    }

    public void commit(String hashParent, String hashRootDirectory, String author, String comment,String path){
        FileHandler fileHandler = FileHandler.getInstance();
        TimeHandler timeHandler = TimeHandler.getInstance();
        this.treeRootHash = hashRootDirectory;
        this.parentCommitHash = hashParent;
        this.secondParentCommitHash = null; //todo need to write a function that return the second commit
        this.comment = comment;
        this.creationTime = timeHandler.getTime();
        this.author = author;
        gitFileSh1 = new ArrayList<>();

        changeFileHowChangeInThisCommit(path);
        this.isFirst  = true;
        String pathRepo = "C:\\Users\\asafr\\OneDrive\\מסמכים\\GitHub\\AVGitProject\\gitRepos";
        fileHandler.replaceContentBetweenCommas(pathRepo,this.lastCommit);
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
        Sha256 sha = Sha256.getInstance();
        String myContent = parentCommitHash+","+secondParentCommitHash+","
                +treeRootHash+","+comment+","+this.creationTime+","+author;

        String myHash = sha.getHash(myContent);
        this.mySh1 = myHash;
        changeHeadFileContent(repositoryPath);
        try (FileWriter writer = new FileWriter("./.AGit/.Object/"+myHash)) {//check //todo change it to the path that get from the user
            writer.write(myContent);
            System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            System.err.println("Failed to open the file for writing.");
        }
    }
    public void changeHeadFileContent(String repositoryPath){
        FileHandler fileHandler = FileHandler.getInstance();
        String fileName = "HEAD";
        String folderPath = repositoryPath + "/.AGit/";
        String filePath = folderPath + File.separator + fileName;
        String lastSh1ContentHeadFile = null;

        File file = new File(filePath);
        if(file.exists()){
           lastSh1ContentHeadFile = fileHandler.getDataBetweenCommas(filePath);
            if (!file.delete()){
             System.err.println("Failed to delete the file.");
         }
        }
        String pathCommit = folderPath + "/.Object/";
        String sh1OfCommitFile = createCommitFile(pathCommit,this.treeRootHash,lastSh1ContentHeadFile,this.comment,this.author,this.creationTime,fileHandler);
         fileHandler.writeToFile(filePath,this.treeRootHash,sh1OfCommitFile);
         this.lastCommit = sh1OfCommitFile;//this sh1 represent the name of the file of the  commit, he found it the head file (important)
    }
    public String createCommitFile(String pathCommit,String root,String lastSh1ContentHeadFile,String comment,String author, String time,FileHandler fileHandler){

        String dataFormatContent =  "Root=" + root +
                ", Last SHA-1 Content Head File=" + lastSh1ContentHeadFile +
                ", Comment=" + comment +
                ", Author=" + author +
                ", Time=" + time;

        Sha256 sha = Sha256.getInstance();
        String sh1OfCommitFile = sha.getHash(dataFormatContent);
        pathCommit = pathCommit + File.separator + sh1OfCommitFile;

        fileHandler.writeToFileForCommit(pathCommit, dataFormatContent);

        return sh1OfCommitFile;
    }

    public void changeFileHowChangeInThisCommit(String path){
        FileHandler fileHandler = FileHandler.getInstance();
        ArrayList<File> files =  fileHandler.listFilesInFolder(path);
        ArrayList<String> sh1AllFileInFiles = calculateSh1ForFileAndFolder(files);
        ArrayList<String> sh1OfFileThatNotExist = checkSh1ExistInDB(sh1AllFileInFiles);//if the sh1 notExist the file changes
        if (sh1OfFileThatNotExist == null){// Nothing change in this commit
            return;
        }
        else {
            // Write to DB the new Sh1 (of the new files)
            fileHandler.writeToDBFile(sh1OfFileThatNotExist);
            ArrayList<File> newFiles = getFileBySh1(sh1OfFileThatNotExist,files);
            this.files = newFiles;
            createTreeAndBlob();
        }
    }

    public void createTreeAndBlob(){

        for (File file:this.files){
            try {
                searchRootDirectory(file);
            } catch (UnknownEntityTypeException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void searchRootDirectory(File file) throws UnknownEntityTypeException {
        String parentDirectoryPath = file.getParentFile().getPath();
        if (parentDirectoryPath.equals(this.nameOfRootDirectory)){
            return;
        } else {
            searchRootDirectory(file.getParentFile());
            FileHandler fileHandler = FileHandler.getInstance();
            Sha256 sha = Sha256.getInstance();
            if (this.isFirst && !(file.isFile())){
                this.isFirst = false;
                this.treeRootHash = sha.getSh1ForRoot(file.getParentFile().getPath());
                this.mySh1 =  sha.getHash(file.getPath());
                FolderFormat entityFormat = new FolderFormat(getFileType(file),this.mySh1,this.author,this.creationTime,file.getName());
                fileHandler.createNewTreeFile(entityFormat,this.treeRootHash);
                changeHeadFileContent(this.nameOfRootDirectory);
                isAFileCreateAZip(file, fileHandler);
            }
            else { //our parent is existing
                if (this.isFirst && file.isFile()){
                    FolderFormat entityFormat = new FolderFormat(getFileType(file),sha.getHash(file.getPath()),this.author,this.creationTime,file.getName());//file.getName()
                    fileHandler.writeToFileBuyName( sha.getSh1ForRoot(file.getParentFile().getPath()),entityFormat);
                    isAFileCreateAZip(file,fileHandler);
                }else {
                    FolderFormat entityFormat = new FolderFormat(getFileType(file),sha.getHash(file.getPath()),this.author,this.creationTime,file.getName());//file.getName()
                    fileHandler.writeToFileBuyName( sha.getHash(file.getParentFile().getPath()),entityFormat);
                    isAFileCreateAZip(file,fileHandler);
                }
            }
        }
    }

    private void isAFileCreateAZip(File file, FileHandler fileHandler) throws UnknownEntityTypeException {
        if (getFileType(file) == EntityType.FILE){
            // Need to create a zip file of the changes file
            if (!fileHandler.zipFileCreatorInTargetPath(file)){
                try {
                    throw new ZipFileCreationException("Failed to create the zip file for the changes file.");
                } catch (ZipFileCreationException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private EntityType getFileType(File file) throws UnknownEntityTypeException {
        if (file.isFile()) {
            return EntityType.FILE;
        } else if (file.isDirectory()) {
            return EntityType.FOLDER;
        } else {
            throw new UnknownEntityTypeException("Unknown entity type for file: " + file.getAbsolutePath());
        }
    }

    public ArrayList<File> getFileBySh1(ArrayList<String> sh1OfFileThatNotExist, ArrayList<File> files){
        ArrayList<File> filteredFiles = new ArrayList<>();
        Sha256 sha = Sha256.getInstance();
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
    }

    public boolean existInDB(String sh1){
        FileHandler fileHandler = FileHandler.getInstance();
        return fileHandler.doesStringExistInFile(sh1);
    }

    public ArrayList<String> calculateSh1ForFileAndFolder(  ArrayList<File> files ){
        Sha256 sha = Sha256.getInstance();
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
