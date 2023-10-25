package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimalTest {

    @ParameterizedTest
    @MethodSource("provideTypeWithPaws")
    @DisplayName("Тест числа лап")
    void paws_ShouldReturnPawsCountForType(Animal.Type type, int expected) {
        Animal animal = new Animal(".", type, Animal.Sex.F, 10, 10, 10, true);

        assertThat(animal.paws())
            .isEqualTo(expected);
    }

    private static Stream<Arguments> provideTypeWithPaws() {
        return Stream.of(
            Arguments.of(Animal.Type.DOG, 4),
            Arguments.of(Animal.Type.CAT, 4),
            Arguments.of(Animal.Type.SPIDER, 8),
            Arguments.of(Animal.Type.FISH, 0),
            Arguments.of(Animal.Type.BIRD, 2)
        );
    }
}
