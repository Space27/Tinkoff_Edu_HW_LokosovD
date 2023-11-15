package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {

    @Test
    @DisplayName("0 потоков")
    void incCounterWithKThreads_shouldReturnZeroForZeroThreads() {
        int k = 0;

        int result = Task1.incCounterWithKThreads(k);

        assertThat(result)
            .isZero();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 40})
    @DisplayName("Несколько потоков")
    void incCounterWithKThreads_shouldReturnKForKThreads(int k) {
        int result = Task1.incCounterWithKThreads(k);

        assertThat(result)
            .isEqualTo(k);
    }
}
