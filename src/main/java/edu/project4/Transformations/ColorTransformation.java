package edu.project4.Transformations;

import edu.project4.Pixel;
import java.util.function.Consumer;

public class ColorTransformation implements Consumer<Pixel> {

    private final int r;
    private final int g;
    private final int b;

    public ColorTransformation(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public void accept(Pixel pixel) {
        if (pixel.getHitCount() == 0) {
            pixel.setR(r);
            pixel.setG(g);
            pixel.setB(b);
        } else {
            pixel.setR((pixel.getR() + r) / 2);
            pixel.setG((pixel.getG() + g) / 2);
            pixel.setB((pixel.getB() + b) / 2);
        }
        pixel.incHitCount();
    }
}
