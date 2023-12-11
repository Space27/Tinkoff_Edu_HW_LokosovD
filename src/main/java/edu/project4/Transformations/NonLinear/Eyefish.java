package edu.project4.Transformations.NonLinear;

import edu.project4.Point;
import edu.project4.Transformations.Transformation;

public class Eyefish implements Transformation {

    @Override
    public Point apply(Point point) {
        double r = 2.0 / (1. + Math.sqrt(point.x() * point.x() + point.y() * point.y()));
        return new Point(r * point.x(), r * point.y());
    }
}
