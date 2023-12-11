package edu.project4;

import edu.project4.Transformations.Transformation;
import edu.project4.Transformations.TransformationGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class TransformationGeneratorTest {

    @RepeatedTest(100)
    @DisplayName("Выдача одного нелинейного преобразования")
    void generateNonLinearTransformations_shouldReturnOneNonLinearTransformation() {
        Transformation transformation = TransformationGenerator.generateNonLinearTransformations(1).getFirst();

        assertThat(TransformationGenerator.NON_LINEAR_TRANSFORMATIONS)
            .contains(transformation);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5, 6, 8, 10, 12, 300})
    @DisplayName("Выдача нескольких нелинейных преобразований")
    void generateNonLinearTransformations_shouldReturnSeveralNonLinearTransformation(int count) {
        List<Transformation> transformations = TransformationGenerator.generateNonLinearTransformations(count);

        assertThat(transformations)
            .doesNotHaveDuplicates()
            .hasSize(Math.min(count, TransformationGenerator.NON_LINEAR_TRANSFORMATIONS.size()))
            .containsAnyElementsOf(TransformationGenerator.NON_LINEAR_TRANSFORMATIONS);
    }

    @RepeatedTest(100)
    @DisplayName("Выдача одного линейного преобразования")
    void getLinearTransformation_shouldReturnLinearTransformation() {
        Transformation transformation = TransformationGenerator.getLinearTransformation();

        assertThat(transformation)
            .isNotNull();
    }
}
