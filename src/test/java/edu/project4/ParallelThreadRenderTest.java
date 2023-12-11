package edu.project4;

import edu.project4.Transformations.TransformationGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.assertj.core.api.Assertions.assertThat;

class ParallelThreadRenderTest {

    @RepeatedTest(100)
    @DisplayName("Генерация картинки")
    void render_shouldChangeImage() {
        Image image = new Image(200, 300);
        Image copy = new Image(image);

        try (ParallelThreadRenderer renderer = new ParallelThreadRenderer()) {
            renderer.render(
                image,
                new Area(-1, -1, 2, 2),
                TransformationGenerator.generateLinTransformationsWithColor(5),
                TransformationGenerator.generateNonLinearTransformations(3),
                5_000,
                (short) 50
            );
        }

        assertThat(image)
            .isNotEqualTo(copy);
    }
}
