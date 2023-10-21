package edu.hw3;

import edu.hw3.Task7.NullComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.TreeMap;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {

    @Test
    @DisplayName("Тест компаратора на дереве")
    void NullComparator_ShouldOperateNullInTreeMapWithAddingOneElement() {
        TreeMap<String, String> tree = new TreeMap<>(new NullComparator<>());

        tree.put(null, "test");

        assertThat(tree.containsKey(null))
            .isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideKeysAndValues")
    @DisplayName("Тест компаратора на дереве с добавлением нескольких элементов")
    void NullComparator_ShouldOperateNullInTreeMapWithAddingSeveralElements(String[] keys, String[] values) {
        TreeMap<String, String> tree = new TreeMap<>(new NullComparator<>());

        for (int i = 0; i < keys.length; ++i) {
            tree.put(keys[i], values[i]);
        }

        assertThat(tree.containsKey(keys[keys.length - 1]))
            .isTrue();
        assertThat(tree.containsValue(values[keys.length - 1]))
            .isTrue();
    }

    private static Stream<Arguments> provideKeysAndValues() {
        return Stream.of(
            Arguments.of(
                new String[] {null, "1", "ab", null, "abc"},
                new String[] {"1", "2", "3", "4", "5"}
            ),
            Arguments.of(
                new String[] {null, "1", "ab", null},
                new String[] {"1", "2", "3", "4"}),
            Arguments.of(
                new String[] {"1", "ab", null, "abc"},
                new String[] {"2", "3", "4", "5"})
        );
    }
}
