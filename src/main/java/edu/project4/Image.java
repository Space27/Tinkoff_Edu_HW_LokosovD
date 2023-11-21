package edu.project4;

public class Image {

    private final Pixel[] data;
    private final int width;
    private final int height;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;

        data = new Pixel[width * height];
        for (int i = 0; i < data.length; ++i) {
            data[i] = new Pixel();
        }
    }

    public Pixel getPixel(int x, int y) {
        if (contains(x, y)) {
            return data[x * width + y];
        } else {
            return null;
        }
    }

    public boolean contains(int x, int y) {
        return 0 <= x && x < width && 0 <= y && y < height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
