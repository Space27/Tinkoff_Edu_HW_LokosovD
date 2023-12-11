package edu.project4;

import edu.project4.Transformations.ColorTransformation;
import edu.project4.Transformations.Transformation;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class OneThreadRenderer implements Renderer {

    private final Random random;
    private final int symmetry;
    private static final int MIN_STEPS = 20;
    private static final double ACCURACY = Math.pow(10, 10);

    public OneThreadRenderer(Random random, int symmetry) {
        this.random = random;
        this.symmetry = symmetry;
    }

    public OneThreadRenderer() {
        this(new SecureRandom(), 1);
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
        for (int num = 0; num < samples; ++num) {
            Point pw = getRandomPointInArea(world);

            for (short step = -MIN_STEPS; step < iterPerSample; ++step) {
                var linTrans = getRandomElementInList(transformations);
                var nonLinTrans = getRandomElementInList(variations);

                pw = linTrans.getKey().apply(pw);
                pw = nonLinTrans.apply(pw);

                if (step > 0) {
                    colorSymmetryPixels(canvas, pw, world, linTrans.getValue());
                }
            }
        }

        return canvas;
    }

    public void colorSymmetryPixels(
        Image canvas,
        Point point,
        Area world,
        ColorTransformation newColor
    ) {
        double theta = 0.0;

        for (int s = 0; s < symmetry; ++s) {
            theta += Math.PI * 2 / symmetry;
            var pwr = rotate(point, theta);
            if (!world.contains(pwr)) {
                continue;
            }

            Pixel pixel = mapRange(world, pwr, canvas);
            if (pixel == null) {
                continue;
            }

            synchronized (pixel) {
                newColor.accept(pixel);
            }
        }
    }

    public Point getRandomPointInArea(Area world) {
        return new Point(random.nextDouble(world.x(), world.xMax()), random.nextDouble(world.y(), world.yMax()));
    }

    public <T> T getRandomElementInList(List<T> list) {
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    public Point rotate(Point point, double theta) {
        double x = Math.round((point.x() * Math.cos(theta) - point.y() * Math.sin(theta)) * ACCURACY) / ACCURACY;
        double y = Math.round((point.x() * Math.sin(theta) + point.y() * Math.cos(theta)) * ACCURACY) / ACCURACY;

        return new Point(x, y);
    }

    public Pixel mapRange(Area world, Point point, Image image) {
        int x = image.getWidth() - (int) (((world.xMax() - point.x()) / world.width()) * image.getWidth());
        int y = image.getHeight() - (int) (((world.yMax() - point.y()) / world.height()) * image.getHeight());

        x = x == image.getWidth() ? x - 1 : x;
        y = y == image.getHeight() ? y - 1 : y;

        return image.getPixel(x, y);
    }
}
