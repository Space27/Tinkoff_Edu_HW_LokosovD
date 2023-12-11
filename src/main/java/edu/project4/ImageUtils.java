package edu.project4;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {

    private ImageUtils() {
    }

    public static void save(Image image, Path filename, ImageFormat format) {
        try {
            ImageIO.write(imgToBufferedImage(image), format.name().toUpperCase(), filename.toFile());
        } catch (IOException ignored) {
        }
    }

    private static BufferedImage imgToBufferedImage(Image image) {
        BufferedImage bi = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Pixel pixel = image.getPixel(j, i);
                bi.setRGB(j, i, pixel.getColor().getColorByOneNum());
            }
        }

        return bi;
    }
}
