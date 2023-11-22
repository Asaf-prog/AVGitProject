package org.example.display;

import org.example.engine.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DisplayData {

    private String path;
    private boolean flag = true;
    private Queue <Commit> commits;

    public DisplayData(String path) {
        this.path = path;
        commits = new LinkedList<>();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void showAllCommitByPath(){
        String lastCommit = getLastCommit();
        presentFromRootTree(lastCommit);
    }

    private void presentFromRootTree(String commit) {
            if (commit == null){
                return;
            } else {
                presentFromRootTree(getParentCommit(commit));
                presentCurrentCommitData(commit);
            }
    }

    private String getParentCommit(String commit) {
        FileHandler fileHandler = FileHandler.getInstance();
        return fileHandler.extractLastCommitFromCommitFile(commit);

    }

    private void presentCurrentCommitData(String commitNameOfFile) {
        //extract the root from the commit file
        String commitPath = path + "/.Agit/.Object" + commitNameOfFile;
        Commit commit = readCommitFromFile(commitPath);
        commit.setName(commitNameOfFile);

        GitTreeNode gitTreeNode = createTreeOfGitFile(path + "/.Agit/.Object" + commit.getRoot());
        GitTreeNode gitTreeNodeFix = createNewFixTreeNode(gitTreeNode);
        commit.setGitTreeNode(gitTreeNodeFix);

    }

    private GitTreeNode createNewFixTreeNode(GitTreeNode gitTreeNode) {
        Queue <GitTreeNode> gitTreeNodeQueue = new LinkedList<>();
        GitTreeNode resGitTreeNode = new GitTreeNode(gitTreeNode.getGitFile());
        createNewFixTreeNodeRec(gitTreeNodeQueue,gitTreeNode);
       //another function
        gitTreeNodeQueue.poll();
        moveQueueToTree(resGitTreeNode,gitTreeNodeQueue);
        return resGitTreeNode;
    }

    private void moveQueueToTree(GitTreeNode resGitTreeNode, Queue<GitTreeNode> gitTreeNodeQueue) {

        ArrayList<GitTreeNode> gitTreeNodes = new ArrayList<>();
        reverseQueueAndMoveToList(gitTreeNodes,gitTreeNodeQueue);
        moveListToTreeNode(resGitTreeNode,gitTreeNodes);
    }

    private void moveListToTreeNode(GitTreeNode resGitTreeNode, ArrayList<GitTreeNode> gitTreeNodes) {
        if (gitTreeNodes.isEmpty()){
            return;
        }else {
            resGitTreeNode.getChildren().get(0).addChild(gitTreeNodes.remove(0));
            moveListToTreeNode(resGitTreeNode.getChildren().get(0),gitTreeNodes);
        }
    }

    private void reverseQueueAndMoveToList(ArrayList<GitTreeNode> gitTreeNodes, Queue<GitTreeNode> gitTreeNodeQueue) {
        if (gitTreeNodeQueue.isEmpty()){
            return;
        }else {
            gitTreeNodes.add(gitTreeNodeQueue.poll());
            reverseQueueAndMoveToList(gitTreeNodes,gitTreeNodeQueue);
        }
    }

    private void createNewFixTreeNodeRec( Queue <GitTreeNode> gitTreeNodeQueue, GitTreeNode gitTreeNode) {
        if (gitTreeNode.getChildren().get(0) == null){

        } else {
            createNewFixTreeNodeRec(gitTreeNodeQueue, gitTreeNode.getChildren().get(0));
            gitTreeNodeQueue.add(gitTreeNode);
        }
    }

    private GitTreeNode createTreeOfGitFile(String rootPath){
        GitFile gitRoot = createTreeOrBlobFromRootPath(rootPath);
        GitTreeNode gitTreeNode = new GitTreeNode(gitRoot);
        for (GitFile gitFile : gitTreeNode.getGitFile().getFiles()){
            if (!gitFile.isBlob()){ // is tree
                gitTreeNode.addChild(createTreeOfGitFile(path + "/.Agit/.Object" + gitFile.getShaOne()));
            }
        }
        return gitTreeNode;
    }

    private GitFile createTreeOrBlobFromRootPath(String filePath) {
       GitFile gitRoot = new gitTree();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                GitFile gitContent = createGitContent(line);
                if (gitContent != null) {
                    gitRoot.addNewFile(gitContent);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gitRoot;
    }

    private GitFile createGitContent(String line) {
        String[] parts = line.split(",");

        if (parts.length == 5) {
            String name = parts[0];
            String hash = parts[1];
            String type = parts[2];
            String author = parts[3];
            String time = parts[4];

            if ("FILE".equals(type)) {
                return new gitBlob(name, hash, author, time);
            } else if ("FOLDER".equals(type)) {
                return new gitTree(name, hash, author, time);
            }
        }
        return null; // Invalid input line, or unsupported type
    }

    private Commit readCommitFromFile (String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Commit commit = new Commit(null, null, null, null, null);

            while ((line = reader.readLine()) != null) {
                parseLine(line, commit);
            }

            return commit;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void parseLine(String line, Commit commit) {
        Pattern pattern = Pattern.compile("(\\w+)=(.*?), ");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);

            switch (key) {
                case "Root":
                    commit.setRoot(value);
                    break;
                case "Last SHA-1 Content Head File":
                    commit.setLastSh1(value.equals("null") ? null : value);
                    break;
                case "Comment":
                    commit.setComment(value);
                    break;
                case "Author":
                    commit.setAuthor(value);
                    break;
                case "Time":
                    commit.setTime(value);
                    break;
                // Add more cases for other properties if needed
            }
        }
    }
    private String getLastCommit() {
        FileHandler fileHandler = FileHandler.getInstance();
        String content = fileHandler.extractStringOfLastCommitBetweenCommas(this.path+"/.Agit/HEAD");
        return content;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Queue<Commit> getCommits() {
        return commits;
    }

    public void setCommits(Queue<Commit> commits) {
        this.commits = commits;
    }
}
