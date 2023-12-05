package edu.project4;

import edu.project4.Transformations.ColorTransformation;
import edu.project4.Transformations.Transformation;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class OneThreadRenderer implements Renderer {

    private static final Random RANDOM = new SecureRandom();
    private static final int MIN_STEPS = 20;
    private static final int SYMMETRY = 1;

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
            Point pw =
                new Point(RANDOM.nextDouble(world.x(), world.xMax()), RANDOM.nextDouble(world.y(), world.yMax()));

            for (short step = -MIN_STEPS; step < iterPerSample; ++step) {
                int linIndex = RANDOM.nextInt(0, transformations.size());
                int nonLinIndex = RANDOM.nextInt(0, variations.size());

                pw = transformations.get(linIndex).getKey().apply(pw);
                pw = variations.get(nonLinIndex).apply(pw);

                if (step > 0) {
                    double theta = 0.0;
                    for (int s = 0; s < SYMMETRY; ++s) {
                        theta += Math.PI * 2 / SYMMETRY;
                        var pwr = rotate(pw, theta);
                        if (!world.contains(pwr)) {
                            continue;
                        }

                        Pixel pixel = mapRange(world, pwr, canvas);
                        if (pixel == null) {
                            continue;
                        }

                        transformations.get(linIndex).getValue().accept(pixel);
                    }
                }
            }
        }

        return canvas;
    }

    public static Point rotate(Point point, double theta) {
        double x = point.x() * Math.cos(theta) - point.y() * Math.sin(theta);
        double y = point.x() * Math.sin(theta) + point.y() * Math.cos(theta);

        return new Point(x, y);
    }

    public static Pixel mapRange(Area world, Point point, Image image) {
        int x = image.getWidth() - (int) (((world.xMax() - point.x()) / world.width()) * image.getWidth());
        int y = image.getHeight() - (int) (((world.yMax() - point.y()) / world.height()) * image.getHeight());

        return image.getPixel(x, y);
    }
}
