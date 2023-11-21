package org.example.execution;

import org.example.dto.GitInitDTO;
import org.example.engine.git;

public class ExecutionTask implements Runnable{
   private GitInitDTO gitInitDTO;

    public ExecutionTask(GitInitDTO gitInitDTO) {
        this.gitInitDTO = gitInitDTO;
    }
    @Override
    public void run() {
        try {
            git gitObject = new git();
            gitObject.gitInit(gitInitDTO.getPath(),gitInitDTO.getRepoName(),gitInitDTO.getComment());
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
