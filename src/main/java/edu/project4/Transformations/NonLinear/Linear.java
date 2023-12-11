package edu.project4.Transformations.NonLinear;

import edu.project4.Point;
import edu.project4.Transformations.Transformation;

public class Linear implements Transformation {

    @Override
    public Point apply(Point point) {
        return point;
    }
}
