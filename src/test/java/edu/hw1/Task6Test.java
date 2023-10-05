package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Число Капрекара")
    void kNum() {
        int number = 6174;

        int digitCount = Task6.countK(number);

        assertThat(digitCount)
            .isEqualTo(0);
    }

    @Test
    @DisplayName("Обычное число")
    void simpleNum() {
        int number = 6621;

        int digitCount = Task6.countK(number);

        assertThat(digitCount)
            .isEqualTo(5);
    }

    @Test
    @DisplayName("Минимальный предел")
    void minLimNum() {
        int number = 1000;

        int digitCount = Task6.countK(number);

        assertThat(digitCount)
            .isEqualTo(0);
    }

    @Test
    @DisplayName("Максимальный предел")
    void maxLimNum() {
        int number = 10000;

        int digitCount = Task6.countK(number);

        assertThat(digitCount)
            .isEqualTo(0);
    }

    @Test
    @DisplayName("Минимальное число с одинаковыми цифрами")
    void minSameDigitsNum() {
        int number = 1111;

        int digitCount = Task6.countK(number);

        assertThat(digitCount)
            .isEqualTo(0);
    }

    @Test
    @DisplayName("Максимальное число с одинаковыми цифрами")
    void maxSameDigitsNum() {
        int number = 9999;

        int digitCount = Task6.countK(number);

        assertThat(digitCount)
            .isEqualTo(0);
    }

    @Test
    @DisplayName("Число с 3 нулями")
    void threeZeroNum() {
        int number = 2000;

        int digitCount = Task6.countK(number);

        assertThat(digitCount)
            .isEqualTo(4);
    }

    @Test
    @DisplayName("Число с 2 нулями посередине")
    void twoZeroNum() {
        int number = 2002;

        int digitCount = Task6.countK(number);

        assertThat(digitCount)
            .isEqualTo(6);
    }

    @Test
    @DisplayName("Число - последовательность")
    void seqNum() {
        int number = 1234;

        int digitCount = Task6.countK(number);

        assertThat(digitCount)
            .isEqualTo(3);
    }
}
