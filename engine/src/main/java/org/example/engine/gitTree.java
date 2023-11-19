package org.example.engine;

import java.io.File;
import java.util.ArrayList;

public class gitTree implements gitFile{
    private ArrayList<gitFile> files;
    private String shaOne;
    private String date;
    private String author;
    private boolean isBlob;
    public gitTree(){
        isBlob = false;
    }
    private String path;
    public gitTree(ArrayList<gitFile> files, String shaOne, String date, String author) {
        this.files = files;
        this.shaOne = shaOne;
        this.date = date;
        this.author = author;
    }
    public gitTree(String shaOne, String date, String author) {
        this.shaOne = shaOne;
        this.date = date;
        this.author = author;
    }
    public gitTree(String path) {
     this.path = path;
    }
    @Override
    public String getShaOne(){
     return shaOne;
    }
    @Override
    public String getDate() {
        return date;
    }

    @Override
    public String getAuthor() {
        return author;
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
}
