package edu.project4;

import edu.project4.Transformations.ColorTransformation;
import edu.project4.Transformations.Transformation;
import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface Renderer {

    Image render(
        Image canvas,
        Area world,
        List<Map.Entry<Transformation, ColorTransformation>> transformations,
        List<Transformation> variations,
        int samples,
        short iterPerSample
    );
}
