package org.example.engine;

import java.io.File;
import java.util.Map;

import static org.example.engine.FileHandler.createFolder;
import static org.example.engine.FileHandler.writeToFile;
//folder(tree) represent by file with data about the Folder the name of the file (Folder) is the SH1
//file(blob) fulfill a zip file of the object, the name of the file is the SH1
//for branch we will create a new file that handle this branch

public class Git {
    private String currentUser = "asaf";
    private Map<String, GitRepository> repositoriesMap;//rep name to object
    private String rootSha1;
    public void gitInit(String path,String repoName,String comment){

        if (!AGitExistInFile(path)) {
            Sha256 sha = Sha256.getInstance();
            this.rootSha1 = sha.getHash(path + repoName);//todo need to save in file as a fair the name of the rep and the sha1
            createFolder(path + "/.AGit/");
            createFolder(path + "/.AGit/.Object");
            writeToFile("./gitRepos", repoName,"null");// Demo db => every session have a repo list
            GitRepository newRep = new GitRepository(currentUser, repoName, path,comment);
            String hash = "hash";
        }else {
            System.out.println("The trace after this folder exist");
        }
    }
    public String getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
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

}
