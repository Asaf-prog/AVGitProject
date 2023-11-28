package org.example.dto;

public class GitInitDTO {
    private String path;
    private String repoName;
    private String comment;
    private String userPassword;

    public GitInitDTO(String path, String repoName, String comment,String userPassword) {
        this.path = path;
        this.repoName = repoName;
        this.comment = comment;
        this.userPassword = userPassword;
    }
    public GitInitDTO() {}

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "GitInitDTO{" +
                "path='" + path + '\'' +
                ", repoName='" + repoName + '\'' +
                ", comment='" + comment + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
