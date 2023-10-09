package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @ParameterizedTest
    @CsvSource({"1853, 4, 990", "1, 1, 1", "8, 1, 1"})
    @DisplayName("Стандартный сдвиг влево")
    void rotateLeft_ShouldRotateNumsInStandardCases(int number, int shift, int expected) {
        int shiftedNum = Task7.rotateLeft(number, shift);

        assertThat(shiftedNum)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"1853, 4, 1779", "1, 1, 1", "8, 1, 4"})
    @DisplayName("Стандартный сдвиг вправо")
    void rotateRight_ShouldRotateNumsInStandardCases(int number, int shift, int expected) {
        int shiftedNum = Task7.rotateRight(number, shift);

        assertThat(shiftedNum)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"0, 0", "0, 1", "0, 8"})
    @DisplayName("Сдвиг нуля влево")
    void rotateLeft_ShouldReturnZeroIfRotatesZero(int number, int shift) {
        int shiftedNum = Task7.rotateLeft(number, shift);

        assertThat(shiftedNum).
            isZero();
    }

    @ParameterizedTest
    @CsvSource({"0, 0", "0, 1", "0, 8"})
    @DisplayName("Сдвиг нуля вправо")
    void rotateRight_ShouldReturnZeroIfRotatesZero(int number, int shift) {
        int shiftedNum = Task7.rotateRight(number, shift);

        assertThat(shiftedNum).
            isZero();
    }

    @ParameterizedTest
    @CsvSource({"53, 9, 46", "19, 5, 19"})
    @DisplayName("Сдвиг вправо больше длины числа")
    void rotateRight_ShouldRotateNumIfShiftHigherThenNumLen(int number, int shift, int expected) {
        int shiftedNum = Task7.rotateRight(number, shift);

        assertThat(shiftedNum).
            isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"53, 8, 23", "19, 5, 19"})
    @DisplayName("Сдвиг влево больше длины числа")
    void rotateLeft_ShouldRotateNumIfShiftHigherThenNumLen(int number, int shift, int expected) {
        int shiftedNum = Task7.rotateLeft(number, shift);

        assertThat(shiftedNum).
            isEqualTo(expected);
    }
}
