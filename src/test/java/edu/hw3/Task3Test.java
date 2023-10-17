package edu.hw3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {

    @ParameterizedTest
    @MethodSource("provideOneLenArr")
    @DisplayName("Список из одного элемента")
    void freqDict_ShouldReturnElementWithOneForOneLenList(ArrayList<Object> list, HashMap<Object, Integer> expMap) {
        HashMap<Object, Integer> map = Task3.freqDict(list);

        assertThat(map).
            isEqualTo(expMap);
    }

    @ParameterizedTest
    @MethodSource("provideArrWithDifferentElements")
    @DisplayName("Список из нескольких разных элементов")
    void freqDict_ShouldReturnElementsWithOneForListWithDifferentElements(
        ArrayList<Object> list,
        HashMap<Object, Integer> expMap
    ) {
        HashMap<Object, Integer> map = Task3.freqDict(list);

        assertThat(map).
            isEqualTo(expMap);
    }

    @ParameterizedTest
    @MethodSource("provideArrWithWithTwoSameElements")
    @DisplayName("Список из двух одинаковых элементов")
    void freqDict_ShouldReturnElementsWithTwoForListWithTwoSameElements(
        ArrayList<Object> list,
        HashMap<Object, Integer> expMap
    ) {
        HashMap<Object, Integer> map = Task3.freqDict(list);

        assertThat(map).
            isEqualTo(expMap);
    }

    @ParameterizedTest
    @MethodSource("provideArr")
    @DisplayName("Корректный список")
    void freqDict_ShouldReturnCorrectMapForValidList(
        ArrayList<Object> list,
        HashMap<Object, Integer> expMap
    ) {
        HashMap<Object, Integer> map = Task3.freqDict(list);

        assertThat(map).
            isEqualTo(expMap);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Список - null")
    void freqDict_ShouldReturnNullForNull(ArrayList<Object> list) {
        assertThrows(IllegalArgumentException.class, () -> Task3.freqDict(list));
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("Список из двух одинаковых элементов")
    void freqDict_ShouldReturnEmptyMapForEmptyList(ArrayList<Object> list) {
        HashMap<Object, Integer> map = Task3.freqDict(list);
        HashMap<Object, Integer> expMap = new HashMap<>();

        assertThat(map).
            isEqualTo(expMap);
    }

    private static Stream<Arguments> provideOneLenArr() {
        return Stream.of(
            Arguments.of(
                new ArrayList<Object>(List.of("hello")),
                new HashMap<Object, Integer>() {{
                    put("hello", 1);
                }}
            ),
            Arguments.of(
                new ArrayList<Object>(List.of(1)),
                new HashMap<Object, Integer>() {{
                    put(1, 1);
                }}
            ),
            Arguments.of(
                new ArrayList<Object>(List.of('c')),
                new HashMap<Object, Integer>() {{
                    put('c', 1);
                }}
            )
        );
    }

    private static Stream<Arguments> provideArrWithDifferentElements() {
        return Stream.of(
            Arguments.of(
                new ArrayList<Object>(List.of("he", "ho")),
                new HashMap<Object, Integer>() {{
                    put("he", 1);
                    put("ho", 1);
                }}
            ),
            Arguments.of(
                new ArrayList<Object>(List.of(1, 2, 3, 4)),
                new HashMap<Object, Integer>() {{
                    put(1, 1);
                    put(2, 1);
                    put(3, 1);
                    put(4, 1);
                }}
            ),
            Arguments.of(
                new ArrayList<Object>(List.of('c', 'a', 'b')),
                new HashMap<Object, Integer>() {{
                    put('a', 1);
                    put('b', 1);
                    put('c', 1);
                }}
            )
        );
    }

    private static Stream<Arguments> provideArrWithWithTwoSameElements() {
        return Stream.of(
            Arguments.of(
                new ArrayList<Object>(List.of("hello", "hello")),
                new HashMap<Object, Integer>() {{
                    put("hello", 2);
                }}
            ),
            Arguments.of(
                new ArrayList<Object>(List.of(1, 1)),
                new HashMap<Object, Integer>() {{
                    put(1, 2);
                }}
            ),
            Arguments.of(
                new ArrayList<Object>(List.of('c', 'c')),
                new HashMap<Object, Integer>() {{
                    put('c', 2);
                }}
            )
        );
    }

    private static Stream<Arguments> provideArr() {
        return Stream.of(
            Arguments.of(
                new ArrayList<Object>(List.of("a", "bb", "a", "bb")),
                new HashMap<Object, Integer>() {{
                    put("a", 2);
                    put("bb", 2);
                }}
            ),
            Arguments.of(
                new ArrayList<Object>(List.of("this", "and", "that", "and")),
                new HashMap<Object, Integer>() {{
                    put("this", 1);
                    put("and", 2);
                    put("that", 1);
                }}
            ),
            Arguments.of(
                new ArrayList<Object>(List.of("код", "код", "код", "bug")),
                new HashMap<Object, Integer>() {{
                    put("код", 3);
                    put("bug", 1);
                }}
            ),
            Arguments.of(
                new ArrayList<Object>(List.of(1, 1, 2, 2, 1, 3)),
                new HashMap<Object, Integer>() {{
                    put(1, 3);
                    put(2, 2);
                    put(3, 1);
                }}
            )
        );
    }
}
