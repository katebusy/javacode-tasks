package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private final ExecutorService executorService;
    private final CyclicBarrier barrier;
    private final int numberOfTasks;
    private final List<Integer> results;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
        this.executorService = Executors.newFixedThreadPool(numberOfTasks);
        this.results = new ArrayList<>();
        this.barrier = new CyclicBarrier(numberOfTasks, () -> {
            System.out.println("All tasks completed. Merging results...");
            int totalResult = results.stream().mapToInt(Integer::intValue).sum();
            System.out.println("Total result: " + totalResult);
        });
    }

    public void executeTasks() {
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfTasks; i++) {
            ComplexTask task = new ComplexTask(i + 1);
            futures.add(executorService.submit(() -> {
                int result = task.execute();
                results.add(result);
                barrier.await();
                return result;
            }));
        }
        for (Future<Integer> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}
