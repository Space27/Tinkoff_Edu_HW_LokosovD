package edu.hw7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

public final class Task4 {

    private Task4() {
    }

    private static final double CIRCLE_CENTER = 0.5;
    private static final double CIRCLE_RAD_SQUARE = 0.25;
    private static final double K = 4.;
    private static final int THREAD_COUNT = 8;
    private static final int TEST_COUNT = 10;

    public static double calculatePi(long iterations) {
        final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
        long hit = 0;
        double dX;
        double dY;

        for (int i = 0; i < iterations; ++i) {
            dX = RANDOM.nextDouble() - CIRCLE_CENTER;
            dY = RANDOM.nextDouble() - CIRCLE_CENTER;

            if (dX * dX + dY * dY <= CIRCLE_RAD_SQUARE) {
                ++hit;
            }
        }

        return K * hit / (iterations > 0 ? iterations : 1);
    }

    public static double calculatePiInParallel(long iterations) {
        LongAdder hit = new LongAdder();

        List<Thread> threadList = new ArrayList<>();
        for (int j = 0; j < THREAD_COUNT; ++j) {
            threadList.add(new Thread(() -> {
                final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
                double dX;
                double dY;

                for (int i = 0; i < iterations / THREAD_COUNT; ++i) {
                    dX = RANDOM.nextDouble() - CIRCLE_CENTER;
                    dY = RANDOM.nextDouble() - CIRCLE_CENTER;

                    if (dX * dX + dY * dY <= CIRCLE_RAD_SQUARE) {
                        hit.increment();
                    }
                }
            }));
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        try {
            for (Thread thread : threadList) {
                thread.join();
            }
        } catch (InterruptedException ignored) {
        }

        return K * hit.longValue() / (iterations > 0 ? iterations : 1);
    }

    public static Result countTheTimeAndErrorForSimplePiCalculation(long iterations) {
        double result = 0;
        double startPoint;
        double time = 0;
        for (int i = 0; i < TEST_COUNT; ++i) {
            startPoint = System.nanoTime();

            result += calculatePi(iterations);

            time += System.nanoTime() - startPoint;
        }

        return new Result(result / TEST_COUNT, Math.abs(result / TEST_COUNT - Math.PI) / Math.PI, time / TEST_COUNT);
    }

    public static Result countTheTimeAndErrorParallelPiCalculation(long iterations) {
        double result = 0;
        double startPoint;
        double time = 0;
        for (int i = 0; i < TEST_COUNT; ++i) {
            startPoint = System.nanoTime();

            result += calculatePiInParallel(iterations);

            time += System.nanoTime() - startPoint;
        }

        return new Result(result / TEST_COUNT, Math.abs(result / TEST_COUNT - Math.PI) / Math.PI, time / TEST_COUNT);
    }

    public record Result(double resPi, double error, double time) {
    }
}
