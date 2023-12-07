package org.example.engine;

import java.io.File;
import java.util.Map;

//folder(tree) represent by file with data about the Folder the name of the file (Folder) is the SH1
//file(blob) fulfill a zip file of the object, the name of the file is the SH1
//for branch we will create a new file that handle this branch

public class Git {
    private String currentUser = "asaf";
    private Map<String, GitRepository> repositoriesMap;//rep name to object
    private String rootSha1;

    public Git() {}

    public Git(String userName) {
        this.currentUser = userName;
    }

    public Git(String currentUser, Map<String, GitRepository> repositoriesMap, String rootSha1) {
        this.currentUser = currentUser;
        this.repositoriesMap = repositoriesMap;
        this.rootSha1 = rootSha1;
    }

    public void gitInit(String path,String repoName,String comment){

        if (!AGitExistInFile(path)) {
            Sha256 sha = Sha256.getInstance();
            this.rootSha1 = sha.getHash(path + repoName);
            createFilesWithInstanceOfFileHandler(path, repoName);
            GitRepository newRep = new GitRepository(currentUser, repoName, path,comment);
        }else {
            System.out.println("The trace after this folder exist");
        }
    }

    private void createFilesWithInstanceOfFileHandler(String path, String repoName) {
        FileHandler fileHandler = FileHandler.getInstance();
        fileHandler.createFolder(path + "/.AGit/");
        fileHandler.createDbFile(path + "/.AGit/db.txt");
        fileHandler.createFolder(path + "/.AGit/.Object");
        fileHandler.writeToFile("./gitRepos", repoName,"null");// Demo db => every session have a repo list

    }

    private boolean AGitExistInFile(String path){
        //need to check if there is no more git-file in the folder when the user try to do the command git init
        File file  = new File(path);
        if (!file.exists()){
            return false;
        }
        if (file.isDirectory()) {
            // Check if the .AGit folder exists
            File agItFolder = new File(path + "/.AGit");
            return agItFolder.exists();
        } else {
            // Check if the file name ends with .AGit
            return file.getName().endsWith(".AGit");
        }
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public Map<String, GitRepository> getRepositoriesMap() {
        return repositoriesMap;
    }

    public void setRepositoriesMap(Map<String, GitRepository> repositoriesMap) {
        this.repositoriesMap = repositoriesMap;
    }

    public void addRepository(String key, GitRepository repository) {
        repositoriesMap.put(key, repository);
    }

    public GitRepository getRepository(String key) {
        return repositoriesMap.get(key);
    }

    public String getRootSha1() {
        return rootSha1;
    }

    public void setRootSha1(String rootSha1) {
        this.rootSha1 = rootSha1;
    }

    //create a new branch, need to supply the repo name, the path,
    //and the commit that you want to create from the new branch
    //than we need to add it to the bd branch
    //if we want to see the last commit we always show the main branch
    //after we merge the two branch we need to delete the branch from the db

}
