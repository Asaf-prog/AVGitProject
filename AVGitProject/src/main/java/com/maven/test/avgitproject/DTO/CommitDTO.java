package com.maven.test.avgitproject.DTO;

public class CommitDTO {
    private String sha1;
    private String name;
    private String date;
    private String author;

    public CommitDTO(String sha1, String name, String date, String author) {
        this.sha1 = sha1;
        this.name = name;
        this.date = date;
        this.author = author;
    }

    public String getSha1() {
        return sha1;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "sha1='" + sha1 + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
