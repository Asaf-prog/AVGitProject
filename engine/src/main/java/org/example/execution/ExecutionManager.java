package org.example.execution;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutionManager {
    Integer numberOfThreads;
    transient ExecutorService threadExecutor;
    private static ExecutionManager instance = null;

    private ExecutionManager(){
        numberOfThreads=5;//default
        threadExecutor = Executors.newFixedThreadPool(numberOfThreads);
    }
    public static ExecutionManager getInstance() {
        if (instance == null){
            instance = new ExecutionManager();
        }
        return instance;
    }

    public Future<?> submitTask(ExecutionTask task) {
        // Assuming executorService.submit returns a Future
        return threadExecutor.submit(task);
    }


    public void executeTask(ExecutionTask executionTask){
        threadExecutor.execute(executionTask);
    }
    public void setNumberOfThreads(Integer numberOfThreads1){
        this.numberOfThreads = numberOfThreads1;
        threadExecutor=Executors.newFixedThreadPool(numberOfThreads);
    }

    public void shutDown() {
        threadExecutor.shutdown();
    }

}
