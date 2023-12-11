package edu.project4.Transformations.NonLinear;

import edu.project4.Point;
import edu.project4.Transformations.Transformation;

public class Cross implements Transformation {

    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(1.0 / ((point.x() * point.x() - point.y() * point.y())
            * (point.x() * point.x() - point.y() * point.y())));
        return new Point(point.x() * r, point.y() * r);
    }
}
