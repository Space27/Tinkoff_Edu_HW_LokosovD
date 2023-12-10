package edu.hw7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class Task1 {

    private Task1() {
    }

    public static int incCounterWithKThreads(int k) {
        AtomicInteger counter = new AtomicInteger(0);

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            threadList.add(new Thread(counter::incrementAndGet));
        }

        for (Thread i : threadList) {
            i.start();
        }

        try {
            for (Thread i : threadList) {
                i.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return counter.intValue();
    }
}
