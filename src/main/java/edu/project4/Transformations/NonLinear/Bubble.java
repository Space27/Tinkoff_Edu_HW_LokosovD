package edu.project4.Transformations.NonLinear;

import edu.project4.Point;
import edu.project4.Transformations.Transformation;

public class Bubble implements Transformation {

    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public Point apply(Point point) {
        double r = 4 + point.x() * point.x() + point.y() * point.y();
        double newX = (4.0 * point.x()) / r;
        double newY = (4.0 * point.y()) / r;
        return new Point(newX, newY);
    }
}
