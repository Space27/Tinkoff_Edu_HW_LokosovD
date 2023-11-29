package edu.project4;

public class Pixel {

    private Color color;
    private int hitCount;
    private double normal;
    private static final double COLOR_SUM = 255 * 3;

    public Pixel(int r, int g, int b, int hitCount, double normal) {
        this(new Color(r, g, b), hitCount, normal);
    }

    public Pixel(Color color, int hitCount, double normal) {
        this.color = color;
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
        return color.r();
    }

    public int getG() {
        return color.g();
    }

    public int getB() {
        return color.b();
    }

    public Color getColor() {
        return color;
    }

    public void setR(int r) {
        this.color = new Color(r, color.g(), color.b());
    }

    public void setG(int g) {
        this.color = new Color(color.r(), g, color.b());
    }

    public void setB(int b) {
        this.color = new Color(color.r(), color.g(), b);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int newHitCount) {
        this.hitCount = newHitCount;
    }

    public void incHitCount() {
        ++this.hitCount;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }
}
