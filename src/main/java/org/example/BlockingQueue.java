package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {
    private Queue<Runnable> queue;
    private int size;

    public BlockingQueue(int size) {
        this.queue = new LinkedList<>();
        this.size = size;
    }

    public synchronized void enqueue(Runnable runnable) throws IllegalArgumentException {
        if (queue.size() == size) {
            throw new IllegalArgumentException();
        }
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
        return queue.size();
    }
}
