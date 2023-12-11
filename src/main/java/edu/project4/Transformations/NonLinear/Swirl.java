package edu.project4.Transformations.NonLinear;

import edu.project4.Point;
import edu.project4.Transformations.Transformation;

public class Swirl implements Transformation {

    @Override
    public Point apply(Point point) {
        double r = point.x() * point.x() + point.y() * point.y();
        double newX = point.x() * Math.sin(r) - point.y() * Math.cos(r);
        double newY = point.x() * Math.cos(r) + point.y() * Math.sin(r);
        return new Point(newX, newY);
    }
}
