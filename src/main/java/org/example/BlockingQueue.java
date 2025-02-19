package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private Queue<T> queue;
    private int size;

    public BlockingQueue(int size) {
        this.queue = new LinkedList<>();
        this.size = size;
    }

    public synchronized void enqueue(T element) throws IllegalArgumentException {
        if (queue.size() == size) {
            System.out.println("Очередь заполнена");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.add(element);
        notify();
    }

    public synchronized T dequeue() {
        if (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        T element = queue.poll();
        notifyAll();
        return element;
    }

    public synchronized int size() {
        return queue.size();
    }
}
