package edu.project4;

public record Area(double x, double y, double width, double height) {

    boolean contains(Point p) {
        return 0 <= p.x() && p.x() < width && 0 <= p.y() && p.y() < height;
    }
}
