package edu.project4;

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
        List<Map.Entry<Transformation, Color>> transformations,
        List<Transformation> variations,
        int samples,
        short iterPerSample
    ) {
        for (int num = 0; num < samples; ++num) {
            Point pw = new Point(
                RANDOM.nextDouble(world.x(), world.x() + world.width()),
                RANDOM.nextDouble(world.y(), world.y() + world.height())
            );

            for (short step = -MIN_STEPS; step < iterPerSample; ++step) {
                int linIndex = RANDOM.nextInt(0, transformations.size());
                int nonLinIndex = RANDOM.nextInt(0, variations.size());

                pw = transformations.get(linIndex).getKey().apply(pw);
                pw = variations.get(nonLinIndex).apply(pw);

                if (step > 0) {
                    double theta2 = 0.0;
                    for (int s = 0; s < SYMMETRY; theta2 += Math.PI * 2 / SYMMETRY, ++s) {
                        var pwr = rotate(pw, theta2);
                        if (!world.contains(pwr)) {
                            continue;
                        }

                        Pixel pixel = mapRange(world, pwr, canvas);
                        if (pixel == null) {
                            continue;
                        }

                        if (pixel.getHitCount() == 0) {
                            pixel.setR(transformations.get(linIndex).getValue().r());
                            pixel.setG(transformations.get(linIndex).getValue().g());
                            pixel.setB(transformations.get(linIndex).getValue().b());
                        } else {
                            pixel.setR((pixel.getR() + transformations.get(linIndex).getValue().r()) / 2);
                            pixel.setG((pixel.getG() + transformations.get(linIndex).getValue().g()) / 2);
                            pixel.setB((pixel.getB() + transformations.get(linIndex).getValue().b()) / 2);
                        }
                        pixel.incHitCount();
                    }
                }
            }
        }

        return canvas;
    }

    private Point rotate(Point point, double theta) {
        double x = point.x() * Math.cos(theta) - point.y() * Math.sin(theta);
        double y = point.x() * Math.sin(theta) + point.y() * Math.cos(theta);

        return new Point(x, y);
    }

    private Pixel mapRange(Area world, Point point, Image image) {
        int x = image.getWidth() - (int) (((world.width() + world.x() - point.x()) / world.width()) * image.getWidth());
        int y =
            image.getHeight() - (int) (((world.height() + world.y() - point.y()) / world.height()) * image.getHeight());

        return image.getPixel(x, y);
    }
}
