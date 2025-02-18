package org.example;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueue {
    private Queue<Runnable> queue;

    public BlockingQueue(int size) {
        this.queue = new ArrayBlockingQueue<>(size);
    }

    public synchronized void enqueue(Runnable runnable) {
        queue.add(runnable);
        notifyAll();
    }

    public synchronized Runnable dequeue() {
        if (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return queue.poll();
    }

    public int size() {
        return this.queue.size();
    }
}
