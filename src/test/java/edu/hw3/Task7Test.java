package edu.hw3;

import edu.hw3.Task7.NullComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class Task7Test {

    @Test
    @DisplayName("Тест компаратора на дереве")
    void NullComparator_shouldOperateNullInTreeMapWithAddingOneElement() {
        Map<String, String> tree = new TreeMap<>(new NullComparator<>());

        tree.put(null, "test");

        assertThat(tree)
            .containsEntry(null, "test");
    }

    @ParameterizedTest
    @MethodSource("provideKeysAndValues")
    @DisplayName("Тест компаратора на дереве с добавлением нескольких элементов")
    void NullComparator_shouldOperateNullInTreeMapWithAddingSeveralElements(String[] keys, String[] values) {
        Map<String, String> tree = new TreeMap<>(new NullComparator<>());

        for (int i = 0; i < keys.length; ++i) {
            tree.put(keys[i], values[i]);

            assertThat(tree)
                .containsEntry(keys[i], values[i]);
        }
    }

    private static Stream<Arguments> provideKeysAndValues() {
        return Stream.of(
            Arguments.of(
                new String[] {null, "1", "ab", null, "abc"},
                new String[] {"1", "2", "3", "4", "5"}
            ),
            Arguments.of(
                new String[] {null, "1", "ab", null},
                new String[] {"1", "2", "3", "4"}
            ),
            Arguments.of(
                new String[] {"1", "ab", null, "abc"},
                new String[] {"2", "3", "4", "5"}
            )
        );
    }
}
