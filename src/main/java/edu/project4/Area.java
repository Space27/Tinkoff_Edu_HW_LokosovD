package edu.project4;

public record Area(double x, double y, double width, double height) {

    boolean contains(Point p) {
        return x <= p.x() && p.x() <= x + width && y <= p.y() && p.y() <= y + height;
    }

    double xMax() {
        return x + width;
    }

    double yMax() {
        return y + height;
    }
}
