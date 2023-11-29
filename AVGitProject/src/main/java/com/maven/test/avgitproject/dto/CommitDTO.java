package com.maven.test.avgitproject.dto;

public class CommitDTO {
    private String nameOfRepo;
    private String path;
    private String userPassword;

    public CommitDTO(){}

    public CommitDTO(String nameOfRepo, String path, String userPassword) {
        this.nameOfRepo = nameOfRepo;
        this.path = path;
        this.userPassword = userPassword;
    }

    public String getNameOfRepo() {
        return nameOfRepo;
    }

    public void setNameOfRepo(String nameOfRepo) {
        this.nameOfRepo = nameOfRepo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
