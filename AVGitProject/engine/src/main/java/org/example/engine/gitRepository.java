package org.example.engine;

import java.util.ArrayList;
import java.util.Map;
import java.time.LocalDate;

public class gitRepository {

    private Map<String,gitCommit> commitMap;
    private ArrayList <String> branch;
    private String HEAD;
    private String name;
    private String author;
    public gitRepository(String author,String name,String path) {
        this.HEAD = null;
        this.name = name;
        gitCommit initCommit = new gitCommit(null,null,author,"repository created",path);
        //this.HEAD = initCommit.getCurrentHash();

    }
    public Map<String, gitCommit> getCommitMap() {
        return commitMap;
    }

    public void setCommitMap(Map<String, gitCommit> commitMap) {
        this.commitMap = commitMap;
    }

    public ArrayList<String> getBranch() {
        return branch;
    }

    public void setBranch(ArrayList<String> branch) {
        this.branch = branch;
    }

    public String getHEAD() {
        return HEAD;
    }

    public void setHEAD(String HEAD) {
        this.HEAD = HEAD;
    }
    public  String CurrentDate() {
        return  LocalDate.now().toString();
    }

    public String getName() {
        return name;
    }

    /// clone
    /// checkout
    /// add
}
