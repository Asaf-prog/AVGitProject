package org.example.dto;

import java.util.ArrayList;
import java.util.List;

public class GitTreeNodeDTO {

    private List<GitFileDTO> gitFileDTOList;
    private List<GitTreeNodeDTO> children;
    public GitTreeNodeDTO(){
        this.children = new ArrayList<>();
    }

    public List<GitFileDTO> getGitFileDTOList() {
        return gitFileDTOList;
    }

    public void setGitFileDTOList(List<GitFileDTO> gitFileDTOList) {
        this.gitFileDTOList = gitFileDTOList;
    }

    public List<GitTreeNodeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<GitTreeNodeDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "GitTreeNodeDTO{" +
                "gitFileDTOList=" + gitFileDTOList +
                ", children=" + children +
                '}';
    }
}
