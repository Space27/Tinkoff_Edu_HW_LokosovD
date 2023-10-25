package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    @ParameterizedTest
    @MethodSource("provideValidAnimal")
    @DisplayName("Нет ошибок в записях")
    void findErrors_ShouldReturnEmptySetForValidRecords(Animal animal) {
        Set<ValidationError> validationErrorSet = Validator.findErrors(animal);

        assertThat(validationErrorSet)
            .isEmpty();
    }

    @ParameterizedTest
    @MethodSource("provideAnimalWithOneError")
    @DisplayName("Одна ошибка в записи")
    void findErrors_ShouldReturnSetWithOneErrorIfAnimalHasOneValidationError(
        Animal animal,
        Set<ValidationError> expected
    ) {
        Set<ValidationError> validationErrorSet = Validator.findErrors(animal);

        assertThat(validationErrorSet)
            .hasSize(1)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideAnimalWithSeveralErrors")
    @DisplayName("Несколько ошибок в записи")
    void findErrors_ShouldReturnSetWithAllErrorsIfAnimalHasSeveralErrors(Animal animal, Set<ValidationError> expected) {
        Set<ValidationError> validationErrorSet = Validator.findErrors(animal);

        assertThat(validationErrorSet)
            .isEqualTo(expected);
    }

    private static Stream<Arguments> provideValidAnimal() {
        return Stream.of(
            Arguments.of(
                new Animal("John Petrucci", Animal.Type.CAT, Animal.Sex.F, 19, 120, 50, true)
            ),
            Arguments.of(
                new Animal("Hetfield", Animal.Type.DOG, Animal.Sex.M, 0, 1, 1, false)
            )
        );
    }

    private static Stream<Arguments> provideAnimalWithOneError() {
        return Stream.of(
            Arguments.of(
                new Animal("", Animal.Type.CAT, Animal.Sex.F, 19, 120, 50, true),
                new HashSet<>(List.of(new ValidationError("Name should contain words", "name")))
            ),
            Arguments.of(
                new Animal(" ", Animal.Type.DOG, Animal.Sex.M, 0, 1, 1, false),
                new HashSet<>(List.of(new ValidationError("Name should contain words", "name")))
            ),
            Arguments.of(
                new Animal("Lars", Animal.Type.SPIDER, Animal.Sex.M, -1, 1, 10, true),
                new HashSet<>(List.of(new ValidationError("Age should be positive number", "age")))
            ),
            Arguments.of(
                new Animal("Jason Newsted", Animal.Type.BIRD, Animal.Sex.M, 30, -100, 1, false),
                new HashSet<>(List.of(new ValidationError("Height should be positive number", "height")))
            ),
            Arguments.of(
                new Animal("Cliff Burton", Animal.Type.FISH, Animal.Sex.M, 1986, 170, -10, false),
                new HashSet<>(List.of(new ValidationError("Weight should be positive number", "weight")))
            )
        );
    }

    private static Stream<Arguments> provideAnimalWithSeveralErrors() {
        return Stream.of(
            Arguments.of(
                new Animal("Till", Animal.Type.CAT, Animal.Sex.F, -1, 0, 50, true),
                new HashSet<>(List.of(
                    new ValidationError("Height should be positive number", "height"),
                    new ValidationError("Age should be positive number", "age")
                ))
            ),
            Arguments.of(
                new Animal(" ", Animal.Type.DOG, Animal.Sex.M, -2, 0, 0, false),
                new HashSet<>(List.of(
                    new ValidationError("Name should contain words", "name"),
                    new ValidationError("Height should be positive number", "height"),
                    new ValidationError("Age should be positive number", "age"),
                    new ValidationError("Weight should be positive number", "weight")
                ))
            )
        );
    }
}
