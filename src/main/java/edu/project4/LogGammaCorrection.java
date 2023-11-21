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
                if (image.getPixel(x, y).getHitCount() != 0) {
                    image.getPixel(x, y).setNormal(log10(image.getPixel(x, y).getHitCount()));
                    if (image.getPixel(x, y).getNormal() > max) {
                        max = image.getPixel(x, y).getNormal();
                    }
                }
            }
        }

        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); ++y) {
                image.getPixel(x, y).setNormal(image.getPixel(x, y).getNormal() / max);

                image.getPixel(x, y)
                    .setR((int) (image.getPixel(x, y).getR() * pow(image.getPixel(x, y).getNormal(), 1 / gamma)));
                image.getPixel(x, y)
                    .setG((int) (image.getPixel(x, y).getG() * pow(image.getPixel(x, y).getNormal(), 1 / gamma)));
                image.getPixel(x, y)
                    .setB((int) (image.getPixel(x, y).getB() * pow(image.getPixel(x, y).getNormal(), 1 / gamma)));
            }
        }
    }
}
