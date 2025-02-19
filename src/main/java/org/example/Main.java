package org.example;

public class Main {
    public static void main(String[] args) {
        Runnable task = () -> System.out.println(Math.random() * 10);
        BlockingQueue<Runnable> blockingQueue = new BlockingQueue<>(4);
        Thread thread1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    blockingQueue.enqueue(task);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(3000);
                    System.out.println("Ждем");
                    blockingQueue.dequeue().run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
