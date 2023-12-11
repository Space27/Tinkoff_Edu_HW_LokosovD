package edu.project4.Transformations.NonLinear;

import edu.project4.Point;
import edu.project4.Transformations.Transformation;

public class Collatz implements Transformation {

    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public Point apply(Point point) {
        double newX = .25 * (1.0 + 4.0 * point.x() - (1.0 + 2.0 * point.x()) * Math.cos(Math.PI * point.x()));
        double newY = .25 * (1.0 + 4.0 * point.y() - (1.0 + 2.0 * point.y()) * Math.cos(Math.PI * point.y()));
        return new Point(newX, newY);
    }
}
