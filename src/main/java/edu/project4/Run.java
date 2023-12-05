package edu.project4;

import edu.project4.Transformations.TransformationGenerator;
import java.nio.file.Path;

public final class Run {

    private Run() {
    }

    @SuppressWarnings({"checkstyle:UncommentedMain", "checkstyle:MagicNumber"})
    public static void main(String[] args) {
        Image image = new Image(1920, 1080);

        try (ParallelThreadRenderer renderer = new ParallelThreadRenderer()) {
            image = renderer.render(
                image,
                new Area(-1, -1, 2, 2),
                TransformationGenerator.generateLinTransformationsWithColor(8),
                TransformationGenerator.generateNonLinearTransformations(2),
                300_000,
                (short) 500
            );
        }
        ImageProcessor processor = new LogGammaCorrection();
        processor.process(image);

        ImageUtils.save(image, Path.of("img.png"), ImageFormat.PNG);
    }
}
