package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {

    @Test
    @DisplayName("Число Капрекара")
    void countK_ShouldReturnZeroForKConst() {
        int number = 6174;

        int stepCount = Task6.countK(number);

        assertThat(stepCount)
            .isEqualTo(0);
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 10000})
    @DisplayName("Предельные числа")
    void countK_ShouldReturnNotCountLimitNums(int number) {
        int stepCount = Task6.countK(number);

        assertThat(stepCount)
            .isEqualTo(-1);
    }

    @ParameterizedTest
    @ValueSource(ints = {1111, 9999, 5555})
    @DisplayName("Числа с одинаковыми цифрами")
    void countK_ShouldReturnNotCountNumsWithSameDigits(int number) {
        int stepCount = Task6.countK(number);

        assertThat(stepCount)
            .isEqualTo(-1);
    }

    @ParameterizedTest
    @CsvSource({"6621, 5", "2000, 4", "2002, 6", "1234, 3"})
    @DisplayName("Корректные числа")
    void countK_ShouldReturnNotCountKIfNumIsValid(int number, int expected) {
        int stepCount = Task6.countK(number);

        assertThat(stepCount)
            .isEqualTo(expected);
    }
}
