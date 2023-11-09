package edu.hw3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task3Test {

    @ParameterizedTest
    @MethodSource("provideOneLenArr")
    @DisplayName("Список из одного элемента")
    void freqDict_shouldReturnElementWithOneForOneLenList(ArrayList<Object> list, Map<Object, Integer> expMap) {
        Map<Object, Integer> map = Task3.freqDict(list);

        assertThat(map).
            isEqualTo(expMap);
    }

    @ParameterizedTest
    @MethodSource("provideArrWithDifferentElements")
    @DisplayName("Список из нескольких разных элементов")
    void freqDict_shouldReturnElementsWithOneForListWithDifferentElements(
        ArrayList<Object> list,
        Map<Object, Integer> expMap
    ) {
        Map<Object, Integer> map = Task3.freqDict(list);

        assertThat(map).
            isEqualTo(expMap);
    }

    @ParameterizedTest
    @MethodSource("provideArrWithTwoSameElements")
    @DisplayName("Список из двух одинаковых элементов")
    void freqDict_shouldReturnElementsWithTwoForListWithTwoSameElements(
        ArrayList<Object> list,
        Map<Object, Integer> expMap
    ) {
        Map<Object, Integer> map = Task3.freqDict(list);

        assertThat(map).
            isEqualTo(expMap);
    }

    @ParameterizedTest
    @MethodSource("provideArr")
    @DisplayName("Корректный список")
    void freqDict_shouldReturnCorrectMapForValidList(
        ArrayList<Object> list,
        Map<Object, Integer> expMap
    ) {
        Map<Object, Integer> map = Task3.freqDict(list);

        assertThat(map).
            isEqualTo(expMap);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Список - null")
    void freqDict_shouldReturnNullForNull(ArrayList<Object> list) {
        assertThrows(IllegalArgumentException.class, () -> Task3.freqDict(list));
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("Пустой список")
    void freqDict_shouldReturnEmptyMapForEmptyList(ArrayList<Object> list) {
        Map<Object, Integer> map = Task3.freqDict(list);
        Map<Object, Integer> expMap = new HashMap<>();

        assertThat(map).
            isEqualTo(expMap);
    }

    private static Stream<Arguments> provideOneLenArr() {
        return Stream.of(
            Arguments.of(
                new ArrayList<Object>(List.of("hello")),
                Map.of("hello", 1)
            ),
            Arguments.of(
                new ArrayList<Object>(List.of(1)),
                Map.of(1, 1)
            ),
            Arguments.of(
                new ArrayList<Object>(List.of('c')),
                Map.of('c', 1)
            )
        );
    }

    private static Stream<Arguments> provideArrWithDifferentElements() {
        return Stream.of(
            Arguments.of(
                new ArrayList<Object>(List.of("he", "ho")),
                Map.of("he", 1,
                    "ho", 1
                )
            ),
            Arguments.of(
                new ArrayList<Object>(List.of(1, 2, 3, 4)),
                Map.of(1, 1,
                    2, 1,
                    3, 1,
                    4, 1
                )
            ),
            Arguments.of(
                new ArrayList<Object>(List.of('c', 'a', 'b')),
                Map.of('c', 1,
                    'a', 1,
                    'b', 1
                )
            )
        );
    }

    private static Stream<Arguments> provideArrWithTwoSameElements() {
        return Stream.of(
            Arguments.of(
                new ArrayList<Object>(List.of("hello", "hello")),
                Map.of("hello", 2)
            ),
            Arguments.of(
                new ArrayList<Object>(List.of(1, 1)),
                Map.of(1, 2)
            ),
            Arguments.of(
                new ArrayList<Object>(List.of('c', 'c')),
                Map.of('c', 2)
            )
        );
    }

    private static Stream<Arguments> provideArr() {
        return Stream.of(
            Arguments.of(
                new ArrayList<Object>(List.of("a", "bb", "a", "bb")),
                Map.of("a", 2,
                    "bb", 2
                )
            ),
            Arguments.of(
                new ArrayList<Object>(List.of("this", "and", "that", "and")),
                Map.of("this", 1,
                    "and", 2,
                    "that", 1
                )
            ),
            Arguments.of(
                new ArrayList<Object>(List.of("код", "код", "код", "bug")),
                Map.of("код", 3,
                    "bug", 1
                )
            ),
            Arguments.of(
                new ArrayList<Object>(List.of(1, 1, 2, 2, 1, 3)),
                Map.of(1, 3,
                    2, 2,
                    3, 1
                )
            )
        );
    }
}
