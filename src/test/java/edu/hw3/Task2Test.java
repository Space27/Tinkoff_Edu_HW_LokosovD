package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task2Test {

    @ParameterizedTest
    @NullSource
    @DisplayName("null строка")
    void clusterize_shouldThrowExceptionForNullStr(String string) {
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(string));
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("Пустая строка")
    void clusterize_shouldReturnEmptyArrayForEmptyArr(String string) {
        List<String> result = Task2.clusterize(string);

        assertThat(result)
            .isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"(", ")", "(()", "())", " )", " (", "( ) )"})
    @DisplayName("В строке есть небалансируемые кластеры")
    void clusterize_shouldThrowExceptionIfClusterCantBeBalanced(String string) {
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(string));
    }

    @ParameterizedTest
    @MethodSource("provideStringsAndClusterizedArrayLists")
    @DisplayName("Корректные строки")
    void clusterize_shouldClusterizeValidStrings(String string, ArrayList<String> expected) {
        List<String> result = Task2.clusterize(string);

        assertThat(result)
            .isEqualTo(expected);
    }

    private static Stream<Arguments> provideStringsAndClusterizedArrayLists() {
        return Stream.of(
            Arguments.of(
                "()()()",
                new ArrayList<>(List.of("()", "()", "()"))
            ),
            Arguments.of(
                "((()))",
                new ArrayList<>(List.of("((()))"))
            ),
            Arguments.of(
                "((())())(()(()()))",
                new ArrayList<>(List.of("((())())", "(()(()()))"))
            ),
            Arguments.of(
                " (-)_-_( )()--",
                new ArrayList<>(List.of(" (-)_-_", "( )", "()--"))
            )
        );
    }
}
