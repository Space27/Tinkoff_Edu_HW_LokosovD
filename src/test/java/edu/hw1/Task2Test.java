package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Простое число")
    void simpleNum() {
        int number = 7;

        short digitCount = Task2.countDigits(number);

        assertThat(digitCount)
            .isEqualTo(1);
    }

    @Test
    @DisplayName("Нулевое число")
    void zeroNum() {
        int number = 0;

        short digitCount = Task2.countDigits(number);

        assertThat(digitCount)
            .isEqualTo(1);
    }

    @Test
    @DisplayName("Отрицательное простое число")
    void simpleNegNum() {
        int number = -5;

        short digitCount = Task2.countDigits(number);

        assertThat(digitCount)
            .isEqualTo(1);
    }

    @Test
    @DisplayName("Пограничное число")
    void limitNum() {
        int number = 10;

        short digitCount = Task2.countDigits(number);

        assertThat(digitCount)
            .isEqualTo(2);
    }

    @Test
    @DisplayName("Большое отрицательное пограничное число")
    void bigNegLimNum() {
        int number = -100000;

        short digitCount = Task2.countDigits(number);

        assertThat(digitCount)
            .isEqualTo(6);
    }

    @Test
    @DisplayName("Большое число")
    void bigNum() {
        int number = 15890;

        short digitCount = Task2.countDigits(number);

        assertThat(digitCount)
            .isEqualTo(5);
    }

    @Test
    @DisplayName("Большое отрицательное число")
    void bigNegNum() {
        int number = -195401;

        short digitCount = Task2.countDigits(number);

        assertThat(digitCount)
            .isEqualTo(6);
    }
}
