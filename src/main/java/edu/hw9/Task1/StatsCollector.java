package edu.hw9.Task1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StatsCollector implements AutoCloseable {

    private final List<StatResult> stats;
    private final ExecutorService threads;
    private final List<Future<?>> tasks;
    private final ReadWriteLock lock;

    public StatsCollector() {
        stats = new Vector<>();
        tasks = new Vector<>();
        threads = Executors.newCachedThreadPool();
        lock = new ReentrantReadWriteLock();
    }

    public void push(String metricName, double[] numbers) {
        lock.readLock().lock();
        try {
            Future<?> task = threads.submit(() -> {
                List<Double> list = Arrays.stream(numbers)
                    .boxed()
                    .toList();

                Future<Double> calcSum =
                    threads.submit(() -> list.parallelStream().mapToDouble(Double::doubleValue).sum());
                Future<Double> calcMin = threads.submit(() -> list.parallelStream()
                    .min(Comparator.naturalOrder())
                    .orElse((double) 0));
                Future<Double> calcMax = threads.submit(() -> list.parallelStream()
                    .max(Comparator.naturalOrder())
                    .orElse((double) 0));

                try {
                    Double sum = calcSum.get();
                    Double min = calcMin.get();
                    Double max = calcMax.get();
                    stats.add(new StatResult(metricName, sum, sum / (list.isEmpty() ? 1 : list.size()), min, max));
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
            tasks.add(task);
        } finally {
            lock.readLock().unlock();
        }
    }

    public List<StatResult> stats() {
        lock.writeLock().lock();
        try {
            Iterator<Future<?>> iterator = tasks.iterator();
            while (iterator.hasNext()) {
                Future<?> task = iterator.next();
                task.get();
                iterator.remove();
            }

            return stats;
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void close() {
        threads.shutdown();
    }

    public record StatResult(String name, double sum, double avg, double min, double max) {
    }
}
