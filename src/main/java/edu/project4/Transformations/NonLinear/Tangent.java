package edu.project4.Transformations.NonLinear;

import edu.project4.Point;
import edu.project4.Transformations.Transformation;

public class Tangent implements Transformation {

    @Override
    public Point apply(Point point) {
        return new Point(Math.sin(point.x()) / Math.cos(point.y()), Math.tan(point.y()));
    }
}
