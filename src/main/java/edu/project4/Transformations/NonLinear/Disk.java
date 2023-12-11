package edu.project4.Transformations.NonLinear;

import edu.project4.Point;
import edu.project4.Transformations.Transformation;

public class Disk implements Transformation {

    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y()) * Math.PI;
        double theta = Math.atan2(point.y(), point.x()) / Math.PI;
        double newX = theta * Math.sin(r);
        double newY = theta * Math.cos(r);
        return new Point(newX, newY);
    }
}
