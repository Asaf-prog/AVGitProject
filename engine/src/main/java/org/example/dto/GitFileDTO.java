package org.example.dto;

public class GitFileDTO {
    private String nameOfTheFile;
    private  String shaOne;
    private String isBlob;
    private String contentOfTheFile;
    private String author;
    private String date;

    public GitFileDTO(String nameOfTheFile, String shaOne, String isBlob, String contentOfTheFile, String author, String date) {
        this.nameOfTheFile = nameOfTheFile;
        this.shaOne = shaOne;
        this.isBlob = isBlob;
        this.contentOfTheFile = contentOfTheFile;
        this.author = author;
        this.date = date;
    }

    public String getNameOfTheFile() {
        return nameOfTheFile;
    }

    public void setNameOfTheFile(String nameOfTheFile) {
        this.nameOfTheFile = nameOfTheFile;
    }

    public String getShaOne() {
        return shaOne;
    }

    public void setShaOne(String shaOne) {
        this.shaOne = shaOne;
    }

    public String getIsBlob() {
        return isBlob;
    }

    public void setIsBlob(String isBlob) {
        this.isBlob = isBlob;
    }

    public String getContentOfTheFile() {
        return contentOfTheFile;
    }

    public void setContentOfTheFile(String contentOfTheFile) {
        this.contentOfTheFile = contentOfTheFile;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "GitFileDTO{" +
                "nameOfTheFile='" + nameOfTheFile + '\'' +
                ", shaOne='" + shaOne + '\'' +
                ", isBlob='" + isBlob + '\'' +
                ", contentOfTheFile='" + contentOfTheFile + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
