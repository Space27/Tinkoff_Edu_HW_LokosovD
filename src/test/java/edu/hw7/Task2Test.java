package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {

    @Test
    @DisplayName("Факториал 0")
    void factorial_shouldReturn1For0() {
        int k = 0;

        long result = Task2.factorial(k);

        assertThat(result)
            .isOne();
    }

    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "3,6", "4,24", "10,3_628_800", "12,479_001_600"})
    @DisplayName("Факториалы чисел больших 0")
    void factorial_shouldReturnCorrectValueForNotZeroValue(int k, long expected){
        long result = Task2.factorial(k);

        assertThat(result)
            .isEqualTo(expected);
    }
}
