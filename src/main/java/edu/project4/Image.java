package edu.project4;

import java.util.Arrays;

public class Image {

    private final Pixel[] data;
    private final int width;
    private final int height;

    public Image(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Weight and Height should be positive");
        }
        this.width = width;
        this.height = height;

        data = new Pixel[width * height];
        for (int i = 0; i < data.length; ++i) {
            data[i] = new Pixel();
        }
    }

    public Image(Image obj) {
        this.width = obj.getWidth();
        this.height = obj.getHeight();

        data = new Pixel[width * height];
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                data[j * width + i] = new Pixel(obj.getPixel(i, j).getColor(),
                    obj.getPixel(i, j).getHitCount(),
                    obj.getPixel(i, j).getNormal()
                );
            }
        }
    }

    public Pixel getPixel(int x, int y) {
        if (contains(x, y)) {
            return data[y * width + x];
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Image image = (Image) obj;

        return Arrays.equals(image.data, this.data) && image.height == height && image.width == width;
    }

    @Override
    public int hashCode() {
        int result = 0;

        for (Pixel pixel : data) {
            result += (pixel.hashCode() % Integer.MAX_VALUE);
        }

        return result;
    }
}
