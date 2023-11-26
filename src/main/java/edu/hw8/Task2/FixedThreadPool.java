package edu.hw8.Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class FixedThreadPool implements ThreadPool {

    private final Thread[] threads;
    private final BlockingQueue<Runnable> queue;
    private volatile boolean stop = false;
    private static final int WAIT_TIME = 500;

    private FixedThreadPool(int threadCount) {
        this.threads = new Thread[threadCount];
        this.queue = new LinkedBlockingQueue<>(threadCount);

        for (int i = 0; i < threadCount; ++i) {
            threads[i] = new Thread(() -> {
                try {
                    while (!stop) {
                        var task = queue.poll(WAIT_TIME, TimeUnit.MILLISECONDS);
                        if (task != null) {
                            task.run();
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    @Override
    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            Thread.sleep(1);
            queue.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        stop = true;
        for (Thread thread : threads) {
            thread.join();
        }
    }

    public static FixedThreadPool create(int threadCount) {
        if (threadCount == 0) {
            return null;
        }
        return new FixedThreadPool(threadCount);
    }
}
