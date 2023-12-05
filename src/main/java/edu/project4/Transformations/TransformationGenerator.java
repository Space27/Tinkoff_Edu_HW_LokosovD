package edu.project4.Transformations;

import edu.project4.Point;
import java.security.SecureRandom;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public final class TransformationGenerator {

    private TransformationGenerator() {
    }

    private static final double RANGE = 1.5;
    private static final double BIG_RANGE = 2;
    private static final int MIN_COLOR = 64;
    private static final int MAX_COLOR = 256;
    private static final Random RANDOM = new SecureRandom();
    @SuppressWarnings("checkstyle:MagicNumber")
    private static final List<Transformation> NON_LINEAR_TRANSFORMATIONS = List.of(
        point -> point,
        point -> new Point(Math.sin(point.x()), Math.sin(point.y())),
        point -> {
            double r = point.x() * point.x() + point.y() * point.y();
            double newX = point.x() * Math.sin(r) - point.y() * Math.cos(r);
            double newY = point.x() * Math.cos(r) + point.y() * Math.sin(r);
            return new Point(newX, newY);
        },
        point -> {
            double r = 1.0 / Math.sqrt(point.x() * point.x() + point.y() * point.y());
            double newX = r * (point.x() - point.y()) * (point.x() + point.y());
            double newY = r * 2.0 * point.x() * point.y();
            return new Point(newX, newY);
        },
        point -> {
            double newX = Math.atan2(point.y(), point.x()) / Math.PI;
            double newY = Math.sqrt(point.x() * point.x() + point.y() * point.y()) - 1.0;
            return new Point(newX, newY);
        },
        point -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            double theta = Math.atan2(point.y(), point.x());
            double newX = r * Math.sin(theta + r);
            double newY = r * Math.cos(theta - r);
            return new Point(newX, newY);
        },
        point -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            double theta = Math.atan2(point.y(), point.x());
            double newX = r * Math.sin(theta * r);
            double newY = -r * Math.cos(theta * r);
            return new Point(newX, newY);
        },
        point -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y()) * Math.PI;
            double theta = Math.atan2(point.y(), point.x()) / Math.PI;
            double newX = theta * Math.sin(r);
            double newY = theta * Math.cos(r);
            return new Point(newX, newY);
        },
        point -> {
            double newX = .25 * (1.0 + 4.0 * point.x() - (1.0 + 2.0 * point.x()) * Math.cos(Math.PI * point.x()));
            double newY = .25 * (1.0 + 4.0 * point.y() - (1.0 + 2.0 * point.y()) * Math.cos(Math.PI * point.y()));
            return new Point(newX, newY);
        },
        point -> {
            double r = Math.sqrt(1.0 / ((point.x() * point.x() - point.y() * point.y())
                * (point.x() * point.x() - point.y() * point.y())));
            return new Point(point.x() * r, point.y() * r);
        },
        point -> new Point(Math.sin(point.x()) / Math.cos(point.y()), Math.tan(point.y())),
        point -> new Point(Math.sin(point.x()), point.y()),
        point -> {
            double r = 4 + point.x() * point.x() + point.y() * point.y();
            double newX = (4.0 * point.x()) / r;
            double newY = (4.0 * point.y()) / r;
            return new Point(newX, newY);
        },
        point -> {
            double r = 2.0 / (1. + Math.sqrt(point.x() * point.x() + point.y() * point.y()));
            return new Point(r * point.x(), r * point.y());
        },
        point -> {
            double newX = Math.cos(Math.PI * point.x()) * Math.cosh(point.y());
            double newY = -Math.sin(Math.PI * point.x()) * Math.sinh(point.y());
            return new Point(newX, newY);
        },
        point -> {
            double newX = Math.exp(point.x() - 1.0) * Math.cos(Math.PI * point.y());
            double newY = Math.exp(point.x() - 1.0) * Math.sin(Math.PI * point.y());
            return new Point(newX, newY);
        },
        point -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            double theta = Math.atan2(point.y(), point.x());
            double newX = (1.0 / r) * (Math.cos(theta) + Math.sin(r));
            double newY = (1.0 / r) * (Math.sin(theta) - Math.cos(r));
            return new Point(newX, newY);
        }
    );

    public static List<Map.Entry<Transformation, ColorTransformation>> generateLinTransformationsWithColor(int count) {
        List<Map.Entry<Transformation, ColorTransformation>> result = new ArrayList<>();

        for (int i = 0; i < count; ++i) {
            Transformation transformation = getLinearTransformation();

            ColorTransformation colorTransformation = new ColorTransformation(
                RANDOM.nextInt(MIN_COLOR, MAX_COLOR),
                RANDOM.nextInt(MIN_COLOR, MAX_COLOR),
                RANDOM.nextInt(MIN_COLOR, MAX_COLOR)
            );

            result.add(new AbstractMap.SimpleEntry<>(transformation, colorTransformation));
        }

        return result;
    }

    public static List<Transformation> generateNonLinearTransformations(int count) {
        List<Transformation> result = new ArrayList<>();
        List<Transformation> transformations = new ArrayList<>(NON_LINEAR_TRANSFORMATIONS);

        for (int i = 0; i < count && !transformations.isEmpty(); ++i) {
            int index = RANDOM.nextInt(0, transformations.size());

            result.add(transformations.get(index));
            transformations.remove(index);
        }

        return result;
    }

    private static Transformation getLinearTransformation() {
        double a;
        double b;
        double e;
        double d;

        do {
            do {
                a = RANDOM.nextDouble(-RANGE, RANGE);
                d = RANDOM.nextDouble(-RANGE, RANGE);
            }
            while (a * a + d * d > 1);
            do {
                b = RANDOM.nextDouble(-RANGE, RANGE);
                e = RANDOM.nextDouble(-RANGE, RANGE);
            }
            while (b * b + e * e > 1);
        }
        while (a * a + b * b + d * d + e * e > 1 + (a * e - d * b) * (a * e - d * b));

        double c = RANDOM.nextDouble(-BIG_RANGE, BIG_RANGE);
        double f = RANDOM.nextDouble(-BIG_RANGE, BIG_RANGE);

        double finalA = a;
        double finalB = b;
        double finalD = d;
        double finalE = e;

        return point -> {
            double newX = point.x() * finalA + point.y() * finalB + c;
            double newY = point.x() * finalD + point.y() * finalE + f;

            return new Point(newX, newY);
        };
    }
}
