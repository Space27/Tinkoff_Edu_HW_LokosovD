package edu.project4;

import edu.project4.Transformations.ColorTransformation;
import edu.project4.Transformations.Transformation;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class ParallelThreadRenderer implements Renderer, AutoCloseable {

    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors() - 2;
    private final ExecutorService threads;
    private static final int SYMMETRY = 1;

    public ParallelThreadRenderer() {
        threads = Executors.newFixedThreadPool(THREAD_COUNT);
    }

    @Override
    public Image render(
        Image canvas,
        Area world,
        List<Map.Entry<Transformation, ColorTransformation>> transformations,
        List<Transformation> variations,
        int samples,
        short iterPerSample
    ) {
        final int samplesPerThread = samples / THREAD_COUNT;

        Callable<Void> task = () -> {
            Renderer renderer = new OneThreadRenderer(ThreadLocalRandom.current(), SYMMETRY);

            renderer.render(canvas, world, transformations, variations, samplesPerThread, iterPerSample);

            return null;
        };

        generateAndCompleteTasks(task);

        return canvas;
    }

    private void generateAndCompleteTasks(Callable<Void> task) {
        List<Callable<Void>> tasks = Stream.generate(() -> task).limit(THREAD_COUNT).toList();
        try {
            List<Future<Void>> futures = threads.invokeAll(tasks);
            for (Future<Void> future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        threads.shutdown();
    }
}
