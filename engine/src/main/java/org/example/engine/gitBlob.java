package org.example.engine;

import java.io.File;
import java.util.ArrayList;

public class gitBlob implements GitFile {
    private String shaOne;
    private String date;
    private String author;
    private boolean isBlob;
    private String path;
    private File file;
    private String nameOfFile;
    public gitBlob(){
        isBlob = true;
    }
    public gitBlob(String author,String shaOne,String date){
        isBlob = true;
        this.author = author;
        this.date =date;
        this.shaOne =shaOne;
    }
    public gitBlob(String path, File file){
        isBlob = true;
        this.path = path;
        this.file =file;
    }

    public gitBlob(String name, String hash, String author, String time) {
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

    public String getNameOfFile() {
        return nameOfFile;
    }

    public void setNameOfFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }
}
