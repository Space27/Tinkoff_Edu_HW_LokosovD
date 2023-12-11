package edu.project4.Transformations.NonLinear;

import edu.project4.Point;
import edu.project4.Transformations.Transformation;

public class Cosine implements Transformation {

    @Override
    public Point apply(Point point) {
        double newX = Math.cos(Math.PI * point.x()) * Math.cosh(point.y());
        double newY = -Math.sin(Math.PI * point.x()) * Math.sinh(point.y());
        return new Point(newX, newY);
    }
}
