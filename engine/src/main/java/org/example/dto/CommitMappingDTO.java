package org.example.dto;

public class CommitMappingDTO {
    private String root;
    private String lastSh1;
    private String comment;
    private String author;
    private String time;

    private GitTreeNodeDTO gitTreeNodeDTO;

    public CommitMappingDTO(){}

    public CommitMappingDTO(String root, String lastSh1, String comment, String author, String time) {
        this.root = root;
        this.lastSh1 = lastSh1;
        this.comment = comment;
        this.author = author;
        this.time = time;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getLastSh1() {
        return lastSh1;
    }

    public void setLastSh1(String lastSh1) {
        this.lastSh1 = lastSh1;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public GitTreeNodeDTO getGitTreeNodeDTO() {
        return gitTreeNodeDTO;
    }

    public void setGitTreeNodeDTO(GitTreeNodeDTO gitTreeNodeDTO) {
        this.gitTreeNodeDTO = gitTreeNodeDTO;
    }

    @Override
    public String toString() {
        return "CommitMappingDTO{" +
                "root='" + root + '\'' +
                ", lastSh1='" + lastSh1 + '\'' +
                ", comment='" + comment + '\'' +
                ", author='" + author + '\'' +
                ", time='" + time + '\'' +
                ", gitTreeNodeDTO=" + gitTreeNodeDTO +
                '}';
    }
}
