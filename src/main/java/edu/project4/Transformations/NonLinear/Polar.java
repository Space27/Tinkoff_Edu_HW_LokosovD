package edu.project4.Transformations.NonLinear;

import edu.project4.Point;
import edu.project4.Transformations.Transformation;

public class Polar implements Transformation {

    @Override
    public Point apply(Point point) {
        double newX = Math.atan2(point.y(), point.x()) / Math.PI;
        double newY = Math.sqrt(point.x() * point.x() + point.y() * point.y()) - 1.0;
        return new Point(newX, newY);
    }
}
