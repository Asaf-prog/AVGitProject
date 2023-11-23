package org.example.engine;

import java.util.ArrayList;
import java.util.List;

public class GitTreeNode {
        private GitFile gitFile;
        private List<GitTreeNode> children = null;

        public GitTreeNode(GitFile gitFile) {
            this.gitFile = gitFile;
            this.children = new ArrayList<>();
        }

        public GitFile getGitFile() {
            return gitFile;
        }

        public List<GitTreeNode> getChildren() {
            return children;
        }

        public void addChild(GitTreeNode child) {
            children.add(child);
        }

        @Override
        public String toString() {
            return gitFile.toString(); // Adjust the output as needed
        }
    }

