package edu.project4;

import static java.lang.StrictMath.log10;
import static java.lang.StrictMath.pow;

public class LogGammaCorrection implements ImageProcessor {

    @Override
    public void process(Image image) {
        double max = 0.0;
        final double gamma = 2.2;

        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); ++y) {
                Pixel pixel = image.getPixel(x, y);
                if (pixel.getHitCount() != 0) {
                    pixel.setNormal(log10(image.getPixel(x, y).getHitCount()));
                    if (pixel.getNormal() > max) {
                        max = pixel.getNormal();
                    }
                }
            }
        }

        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); ++y) {
                Pixel pixel = image.getPixel(x, y);

                pixel.setNormal(image.getPixel(x, y).getNormal() / max);

                pixel.setR((int) (pixel.getR() * pow(pixel.getNormal(), 1 / gamma)));
                pixel.setG((int) (pixel.getG() * pow(pixel.getNormal(), 1 / gamma)));
                pixel.setB((int) (pixel.getB() * pow(pixel.getNormal(), 1 / gamma)));
            }
        }
    }
}
