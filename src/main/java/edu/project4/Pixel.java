package edu.project4;

public class Pixel {

    private int r;
    private int g;
    private int b;
    private int hitCount;
    private double normal;
    private static final double COLOR_SUM = 255 * 3;

    public Pixel(int r, int g, int b, int hitCount, double normal) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.hitCount = hitCount;
        this.normal = normal;
    }

    public Pixel(int r, int g, int b) {
        this(r, g, b, 0, (r + g + b) / COLOR_SUM);
    }

    public Pixel() {
        this(0, 0, 0);
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int newHitCount) {
        this.hitCount = newHitCount;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }
}
