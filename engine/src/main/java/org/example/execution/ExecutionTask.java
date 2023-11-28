package org.example.execution;

import org.example.dto.GitInitDTO;
import org.example.engine.Git;

public class ExecutionTask implements Runnable{
   private GitInitDTO gitInitDTO;

    public ExecutionTask(GitInitDTO gitInitDTO) {
        this.gitInitDTO = gitInitDTO;
    }

    @Override
    public void run() {
        try {
            Git gitObject = new Git();
            gitObject.gitInit(gitInitDTO.getPath(),gitInitDTO.getRepoName(),gitInitDTO.getComment());

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
