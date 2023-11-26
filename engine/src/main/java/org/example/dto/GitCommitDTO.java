package org.example.dto;

public class GitCommitDTO {

    private String hashParent;
    private String hasRootDirectory;
    private String author;
    private String comment;
    private String path;
    private String repoName;

    public GitCommitDTO(String hashParent, String hasRootDirectory, String author, String comment, String path, String repoName) {
        this.hashParent = hashParent;
        this.hasRootDirectory = hasRootDirectory;
        this.author = author;
        this.comment = comment;
        this.path = path;
        this.repoName = repoName;
    }
    public GitCommitDTO(){}
    public String getHashParent() {
        return hashParent;
    }

    public void setHashParent(String hashParent) {
        this.hashParent = hashParent;
    }

    public String getHasRootDirectory() {
        return hasRootDirectory;
    }

    public void setHasRootDirectory(String hasRootDirectory) {
        this.hasRootDirectory = hasRootDirectory;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
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
}
