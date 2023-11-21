package org.example.dto;

public class GitInitDTO {
    private String path;
    private String repoName;
    private String comment;

    public GitInitDTO(String path, String repoName, String comment) {
        this.path = path;
        this.repoName = repoName;
        this.comment = comment;
    }

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
}
