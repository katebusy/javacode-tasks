package org.example;


public class ComplexTask {
    private final int taskId;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    public int execute() {
        int result = (int) (Math.random() * 100);
        System.out.println("Task " + taskId + " executed with result: " + result);
        return result;
    }

    public int getTaskId() {
        return taskId;
    }
}
