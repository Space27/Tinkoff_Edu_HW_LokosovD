package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Число с маленькими цифрами")
    void smallDigitNum() {
        int number = 11211230;

        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("Число с большими цифрами")
    void bigDigitNum() {
        int number = 979710; /* 979710 -> 16161 */

        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("Маленькое число")
    void smallNum() {
        int number = 7;

        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("Маленький палиндром")
    void smallPalindromeNum() {
        int number = 11;

        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("Маленький не-палиндром")
    void smallNotPalindromeNum() {
        int number = 12;

        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("Легкий палиндром")
    void simplePalindromeNum() {
        int number = 12321;

        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("Большой палиндром нечетной длины")
    void bigOddPalindromeNum() {
        int number = 1234554321;

        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("Трехзначное число")
    void threeDigitNum() {
        int number = 321;

        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("Трехзначное число - не предок палиндрома")
    void threeDigitNotNum() {
        int number = 123;

        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("Четырехзначное число")
    void fourDigitNum() {
        int number = 5326;

        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("Четырехзначное число - не предок палиндрома")
    void fourDigitNotNum() {
        int number = 5426;

        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isEqualTo(false);
    }
}
