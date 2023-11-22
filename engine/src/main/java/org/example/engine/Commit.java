package org.example.engine;

import java.util.ArrayList;
import java.util.List;

public class Commit {

    private String root;
    private String name;

    private String lastSh1;

    private String comment;

    private String author;

    private String time;
    private List<GitFile> GitFiles;
    private GitTreeNode gitTreeNode;

    public Commit(String root, String lastSh1, String comment, String author, String time) {
        this.root = root;
        this.lastSh1 = lastSh1;
        this.comment = comment;
        this.author = author;
        this.time = time;
        GitFiles = new ArrayList<>();
    }

    public Commit(String root, String name, String lastSh1, String comment, String author, String time) {
        this.root = root;
        this.name = name;
        this.lastSh1 = lastSh1;
        this.comment = comment;
        this.author = author;
        this.time = time;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getLastSh1() {
        return lastSh1;
    }

    public void setLastSh1(String lastSh1) {
        this.lastSh1 = lastSh1;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<GitFile> getGitFiles() {
        return GitFiles;
    }

    public void setGitFiles(List<GitFile> GitFiles) {
        this.GitFiles = GitFiles;
    }

    public GitTreeNode getGitTreeNode() {
        return gitTreeNode;
    }

    public void setGitTreeNode(GitTreeNode gitTreeNode) {
        this.gitTreeNode = gitTreeNode;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "root='" + root + '\'' +
                ", name='" + name + '\'' +
                ", lastSh1='" + lastSh1 + '\'' +
                ", comment='" + comment + '\'' +
                ", author='" + author + '\'' +
                ", time='" + time + '\'' +
                ", GitFiles=" + GitFiles +
                ", gitTreeNode=" + gitTreeNode +
                '}';
    }
}
