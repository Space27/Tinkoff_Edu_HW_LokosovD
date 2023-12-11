package edu.project4.Transformations.NonLinear;

import edu.project4.Point;
import edu.project4.Transformations.Transformation;

public class Horseshoe implements Transformation {

    @Override
    public Point apply(Point point) {
        double r = 1.0 / Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double newX = r * (point.x() - point.y()) * (point.x() + point.y());
        double newY = r * 2.0 * point.x() * point.y();
        return new Point(newX, newY);
    }
}
