package org.example.engine;

import java.util.ArrayList;
import java.util.Map;
import java.time.LocalDate;

public class GitRepository {

    private Map<String, GitCommit> commitMap;
    private ArrayList <String> branch;
    private String HEAD;
    private String name;
    private String author;

    public GitRepository(String author, String name, String path, String comment) {
        this.HEAD = null;
        this.name = name;
        this.author = author;
        GitCommit initCommit = new GitCommit(null,null,author,comment,path,name);

    }
    public Map<String, GitCommit> getCommitMap() {
        return commitMap;
    }

    public void setCommitMap(Map<String, GitCommit> commitMap) {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "GitRepository{" +
                "commitMap=" + commitMap +
                ", branch=" + branch +
                ", HEAD='" + HEAD + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
