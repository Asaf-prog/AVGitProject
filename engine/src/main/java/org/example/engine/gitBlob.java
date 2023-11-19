package org.example.engine;

import java.io.File;

public class gitBlob implements gitFile {
    private String shaOne;
    private String date;
    private String author;
    private boolean isBlob;
    private String path;
    private File file;
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
        return this.file;
    }

}
