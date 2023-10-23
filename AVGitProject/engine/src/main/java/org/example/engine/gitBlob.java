package org.example.engine;

public class gitBlob implements gitFile {
    private String shaOne;
    private String date;
    private String author;
    private boolean isBlob;
    public gitBlob(){
        isBlob = true;
    }
    public gitBlob(String author,String shaOne,String date){
        isBlob = true;
        this.author = author;
        this.date =date;
        this.shaOne =shaOne;
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
    public void newBlob(){

    }
}
