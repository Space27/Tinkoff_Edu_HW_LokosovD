package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @ParameterizedTest
    @ValueSource(ints = {123, 12565})
    @DisplayName("Не предки палиндромов нечетной длины")
    void isPalindromeDescendant_ShouldReturnTrueForNotPalindromeDescendantOddLen(int number) {
        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {-5426, 5426, 98})
    @DisplayName("Не предки палиндромов четной длины")
    void isPalindromeDescendant_ShouldReturnTrueForNotPalindromeDescendantEvenLen(int number) {
        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {321, 63315, 1338897})
    @DisplayName("Предки палиндромов с нечетной длиной")
    void isPalindromeDescendant_ShouldReturnTrueForPalindromeDescendantOddLen(int number) {
        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {5326, 11211230, 979710, -11211230, 12566})
    @DisplayName("Предки палиндромов с четной длиной")
    void isPalindromeDescendant_ShouldReturnTrueForPalindromeDescendantEvenLen(int number) {
        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isTrue();
    }

    @Test
    @DisplayName("Ноль")
    void isPalindromeDescendant_ShouldReturnFalseForZero() {
        int number = 0;

        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 1221, 1111, 123321})
    @DisplayName("Палиндромы четной длины")
    void isPalindromeDescendant_ShouldReturnTrueForEvenLenPalindromes(int number) {
        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {111, 121, 1234554321, 12321})
    @DisplayName("Палиндромы нечетной длины")
    void isPalindromeDescendant_ShouldReturnTrueForOddLenPalindromes(int number) {
        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {112, 2111, 123, 12322})
    @DisplayName("Не палиндромы нечетной длины")
    void isPalindromeDescendant_ShouldReturnFalseForOddLenNotPalindromes(int number) {
        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {12, 1231, 2223})
    @DisplayName("Не палиндромы четной длины")
    void isPalindromeDescendant_ShouldReturnFalseForEvenLenNotPalindromes(int number) {
        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 7, 9})
    @DisplayName("Не палиндромы четной длины")
    void isPalindromeDescendant_ShouldReturnFalseForOneDigitNums(int number) {
        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);

        assertThat(isPalindromeDescendant)
            .isFalse();
    }
}
