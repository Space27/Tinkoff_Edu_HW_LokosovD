package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Нулевое число")
    void countDigits_ShouldCountZeroIfNumIsZero() {
        int number = 0;

        int digitCount = Task2.countDigits(number);

        assertThat(digitCount)
            .isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {7, -5})
    @DisplayName("Однозначное число")
    void countDigits_ShouldReturnOneIfNumIsSingleDigit(int number) {
        int digitCount = Task2.countDigits(number);

        assertThat(digitCount)
            .isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource({"10, 2", "100, 3", "-10, 2", "-1000, 4"})
    @DisplayName("Предельное число")
    void countDigits_ShouldReturnOneIfNumIsDigitLimit(int number, int expected) {
        int digitCount = Task2.countDigits(number);

        assertThat(digitCount)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"15386, 5", "43253453, 8", "-1254353243, 10"})
    @DisplayName("Большое число")
    void countDigits_ShouldCountDigitsIfNumIsBig(int number, int expected) {
        int digitCount = Task2.countDigits(number);

        assertThat(digitCount)
            .isEqualTo(expected);
    }
}
