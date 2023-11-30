package org.example.engine;

import java.io.File;
import java.util.ArrayList;

public class GitBlob implements GitFile {
    private String shaOne;
    private String date;
    private String author;
    private boolean isBlob;
    private String path;
    private File file;
    private String contentOfFile;
    private String nameOfFile;
    public GitBlob(){
        isBlob = true;
    }
    public GitBlob(String author, String shaOne, String date){
        isBlob = true;
        this.author = author;
        this.date =date;
        this.shaOne =shaOne;
    }
    public GitBlob(String path, File file){
        isBlob = true;
        this.path = path;
        this.file =file;
    }

    public GitBlob(String name, String hash, String author, String time) {
        isBlob = true;
        this.author = author;
        this.date = time;
        this.shaOne = hash;
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
        return this.file;
    }

    @Override
    public void addNewFile(GitFile file) {
        return;
    }

    @Override
    public ArrayList<GitFile> getFiles() {
        return null;
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
        FileHandler fileHandler = FileHandler.getInstance();
        String content = fileHandler.readContentFromZip(fileHandler.getPath(),this.shaOne,this.nameOfFile);
        this.contentOfFile = content;
        System.out.println("content: "+this.contentOfFile);
    }
    @Override
    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String getContentOfTheFile() {
        return this.contentOfFile;
    }
}
