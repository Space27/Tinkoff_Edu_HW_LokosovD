package edu.project4;

import edu.project4.Transformations.TransformationGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class LogGammaCorrectionTest {

    @Test
    @DisplayName("Тест для дефолтного изображения")
    void process_shouldReturnSameImageForBlackImage() {
        ImageProcessor processor = new LogGammaCorrection();
        Image image = new Image(200, 300);
        Image expected = new Image(200, 300);

        processor.process(image);

        assertThat(image)
            .isEqualTo(expected);
    }

    @RepeatedTest(10)
    @DisplayName("Тест для сгенерированной картинки")
    void process_shouldReturnDarkerImage() {
        ImageProcessor processor = new LogGammaCorrection();
        Renderer renderer = new OneThreadRenderer();
        Image image = new Image(200, 300);
        renderer.render(
            image,
            new Area(-1, -1, 2, 2),
            TransformationGenerator.generateLinTransformationsWithColor(5),
            TransformationGenerator.generateNonLinearTransformations(3),
            5_000,
            (short) 50
        );
        Image copy = new Image(image);

        processor.process(image);

        assertThat(image)
            .isNotEqualTo(copy);
    }
}
