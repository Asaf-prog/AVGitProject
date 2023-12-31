package org.example.engine;

import java.io.File;
import java.util.ArrayList;

public class GitTree implements GitFile {
    private ArrayList<GitFile> files;
    private String shaOne;
    private String date;
    private String author;
    private boolean isBlob;
    private String path;
    private String nameOfFile;
    public GitTree(){
        isBlob = false;
        files = new ArrayList<>();
    }
    public GitTree(ArrayList<GitFile> files, String shaOne, String date, String author) {
        this.files = files;
        this.shaOne = shaOne;
        this.date = date;
        this.author = author;
        isBlob = false;
    }
    public GitTree(String shaOne, String date, String author) {
        this.shaOne = shaOne;
        this.date = date;
        this.author = author;
        isBlob = false;
    }
    public GitTree(String path) {
     this.path = path;
    }

    public GitTree(String name, String hash, String author, String time) {
        this.shaOne = hash;
        this.date = time;
        this.author = author;
        isBlob = false;
        this.nameOfFile = name;
    }

    @Override
    public String getShaOne(){
     return shaOne;
    }

    @Override
    public void setShaOne(String sh1) {
        this.shaOne = sh1;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void setDate(String data) {
        this.date = date;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean isBlob() {
        return isBlob;
    }
    @Override
    public String getPath() {
        return this.path;
    }
    @Override
    public File getFile() {
        return null;
    }

    @Override
    public void addNewFile(GitFile file) {
        files.add(file);
    }

    @Override
    public ArrayList<GitFile> getFiles() {
        return files;
    }
    @Override
    public String getNameOfTheFile() {
        return nameOfFile;
    }
    @Override

    public void setNameOfFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }

    @Override
    public void showContentFile() {
        return;
    }

    @Override
    public void setFile(File file) {
        return;
    }

    @Override
    public String getContentOfTheFile() {
        return "it's Folder";
    }
}
