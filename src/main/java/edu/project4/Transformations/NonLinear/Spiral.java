package edu.project4.Transformations.NonLinear;

import edu.project4.Point;
import edu.project4.Transformations.Transformation;

public class Spiral implements Transformation {

    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan2(point.y(), point.x());
        double newX = (1.0 / r) * (Math.cos(theta) + Math.sin(r));
        double newY = (1.0 / r) * (Math.sin(theta) - Math.cos(r));
        return new Point(newX, newY);
    }
}
