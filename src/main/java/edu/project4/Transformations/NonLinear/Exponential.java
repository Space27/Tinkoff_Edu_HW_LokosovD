package edu.project4.Transformations.NonLinear;

import edu.project4.Point;
import edu.project4.Transformations.Transformation;

public class Exponential implements Transformation {

    @Override
    public Point apply(Point point) {
        double newX = Math.exp(point.x() - 1.0) * Math.cos(Math.PI * point.y());
        double newY = Math.exp(point.x() - 1.0) * Math.sin(Math.PI * point.y());
        return new Point(newX, newY);
    }
}
