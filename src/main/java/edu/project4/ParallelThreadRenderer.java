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

    private static final int THREAD_COUNT = 10;
    private final ExecutorService threads;
    private static final int MIN_STEPS = 20;
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
            final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
            for (int num = 0; num < samplesPerThread; ++num) {
                Point pw =
                    new Point(RANDOM.nextDouble(world.x(), world.xMax()), RANDOM.nextDouble(world.y(), world.yMax()));

                for (short step = -MIN_STEPS; step < iterPerSample; ++step) {
                    final int linIndex = RANDOM.nextInt(0, transformations.size());
                    final int nonLinIndex = RANDOM.nextInt(0, variations.size());

                    pw = transformations.get(linIndex).getKey().apply(pw);
                    pw = variations.get(nonLinIndex).apply(pw);

                    if (step > 0) {
                        double theta = 0.0;
                        for (int s = 0; s < SYMMETRY; ++s) {
                            theta += Math.PI * 2 / SYMMETRY;
                            final Point pwr = OneThreadRenderer.rotate(pw, theta);
                            if (!world.contains(pwr)) {
                                continue;
                            }

                            Pixel pixel = OneThreadRenderer.mapRange(world, pwr, canvas);
                            if (pixel == null) {
                                continue;
                            }

                            synchronized (pixel) {
                                transformations.get(linIndex).getValue().accept(pixel);
                            }
                        }
                    }
                }
            }
            return null;
        };

        List<Callable<Void>> tasks = Stream.generate(() -> task).limit(THREAD_COUNT).toList();
        try {
            List<Future<Void>> futures = threads.invokeAll(tasks);
            for (Future<Void> future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return canvas;
    }

    public void close() {
        threads.shutdown();
    }
}
