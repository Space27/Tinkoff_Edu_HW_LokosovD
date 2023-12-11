package edu.project4.Transformations;

import edu.project4.Point;
import edu.project4.Transformations.NonLinear.Bubble;
import edu.project4.Transformations.NonLinear.Collatz;
import edu.project4.Transformations.NonLinear.Cosine;
import edu.project4.Transformations.NonLinear.Cross;
import edu.project4.Transformations.NonLinear.Cylinder;
import edu.project4.Transformations.NonLinear.Disk;
import edu.project4.Transformations.NonLinear.Exponential;
import edu.project4.Transformations.NonLinear.Eyefish;
import edu.project4.Transformations.NonLinear.Handkerchief;
import edu.project4.Transformations.NonLinear.Heart;
import edu.project4.Transformations.NonLinear.Horseshoe;
import edu.project4.Transformations.NonLinear.Linear;
import edu.project4.Transformations.NonLinear.Polar;
import edu.project4.Transformations.NonLinear.Sinusoidal;
import edu.project4.Transformations.NonLinear.Spiral;
import edu.project4.Transformations.NonLinear.Swirl;
import edu.project4.Transformations.NonLinear.Tangent;
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
    public static final List<Transformation> NON_LINEAR_TRANSFORMATIONS = List.of(
        new Linear(),
        new Sinusoidal(),
        new Swirl(),
        new Horseshoe(),
        new Polar(),
        new Handkerchief(),
        new Heart(),
        new Disk(),
        new Collatz(),
        new Cross(),
        new Tangent(),
        new Cylinder(),
        new Bubble(),
        new Eyefish(),
        new Cosine(),
        new Exponential(),
        new Spiral()
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

    public static Transformation getLinearTransformation() {
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
