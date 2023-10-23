package org.example.engine;

import java.util.Map;

import static org.example.engine.FileHandler.createFolder;
import static org.example.engine.FileHandler.writeToFile;
//folder(tree) represent by file with data about the Folder the name of the file (Folder) is the SH1
//file(blob) fulfill a zip file of the object, the name of the file is the SH1
//for branch we will create a new file that handle this branch

public class git {
    private String currentUser = "bla-bla";
    private Map<String, gitRepository> repositoriesMap;//rep name to object
    private String rootSha1;
    public void gitInit(String path,String repoName){
        //todo check if the repo name is uniq
        //todo need to check that the AGit file not exist in the folder
        if (!AGitExistInFile()) {

            sha256 sha = sha256.getInstance();
            this.rootSha1 = sha.getHash(path + repoName);//todo need to save in file as a fair the name of the rep and the sha1
            createFolder("./.AGit/");
            createFolder("./.AGit/.Object");
            gitRepository newRep = new gitRepository(currentUser, repoName, path);
            String hash = "hash";
            //   addRepository(hash,newRep);
            // writeToFile("C:\\Users\\asafr\\Desktop\\codeBooks\\315f5bdb76d078c43b8ac0064e4a0164612b1fce77c869345bfc94c75894edd3","blabla");
            writeToFile("./gitRepos", repoName);

            // let's create a head file that point on the sha1 of the last commit
            // when the program run, and we are going to exist repo, and we need to load the head file to know the sh1 of the last commit
            createFolder("./.AGit/");
        }
    }
    public String getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
    private boolean AGitExistInFile(){
       //need to check if there is no more git-file in the folder when the user try to do the command git init
        return false;
    }
    public Map<String, gitRepository> getRepositoriesMap() {
        return repositoriesMap;
    }

    public void setRepositoriesMap(Map<String, gitRepository> repositoriesMap) {
        this.repositoriesMap = repositoriesMap;
    }
    public void addRepository(String key, gitRepository repository) {
        repositoriesMap.put(key, repository);
    }
    public gitRepository getRepository(String key) {
        return repositoriesMap.get(key);
    }

}
