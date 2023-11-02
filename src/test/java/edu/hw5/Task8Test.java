package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустая или null строка для нечетной длины")
    void isLenOdd_ShouldReturnFalseForEmptyOrNullString(String string) {
        boolean matches = Task8.isLenOdd(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "111", "000", "001", "010", "011", "100", "101", "00000", "11111", "10001",
        "01110", "00100", "11011"})
    @DisplayName("Строка нечетной длины")
    void isLenOdd_ShouldReturnTrueForValidString(String string) {
        boolean matches = Task8.isLenOdd(string);

        assertThat(matches)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "00", "01", "01", "0000", "1111", "1000", "0111", "0010", "1101"})
    @DisplayName("Строка четной длины")
    void isLenOdd_ShouldReturnFalseForEvenLenString(String string) {
        boolean matches = Task8.isLenOdd(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "121", "020", "201", "012", "021", "120", "121", "00002", "21111", "10201",
        "01112", "02100", "11211"})
    @DisplayName("Строка содержит символы не из алфавита")
    void isLenOdd_ShouldReturnFalseIfStringContainsNotAlphabetChars(String string) {
        boolean matches = Task8.isLenOdd(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустая или null строка для 0 и нечетной или 1 и четной")
    void has0AtStartAndOddLenOr1AtStartAndEvenLen_ShouldReturnFalseForEmptyOrNullString(String string) {
        boolean matches = Task8.has0AtStartAndOddLenOr1AtStartAndEvenLen(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "000", "011", "001", "010", "00000", "01111", "01100"})
    @DisplayName("Начинается с 0 нечетной длины")
    void has0AtStartAndOddLenOr1AtStartAndEvenLen_ShouldReturnTrueFor0AtStartOddLenString(String string) {
        boolean matches = Task8.has0AtStartAndOddLenOr1AtStartAndEvenLen(string);

        assertThat(matches)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"10", "11", "1111", "1000", "1001", "1100", "1011", "100000", "111111"})
    @DisplayName("Начинается с 1 четной длины")
    void has0AtStartAndOddLenOr1AtStartAndEvenLen_ShouldReturnTrueFor1AtStartEvenLenString(String string) {
        boolean matches = Task8.has0AtStartAndOddLenOr1AtStartAndEvenLen(string);

        assertThat(matches)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"01", "00", "01", "0000", "0111", "0110"})
    @DisplayName("Начинается с 0 четной длины")
    void has0AtStartAndOddLenOr1AtStartAndEvenLen_ShouldReturnFalseFor0AtStartEvenLenString(String string) {
        boolean matches = Task8.has0AtStartAndOddLenOr1AtStartAndEvenLen(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "111", "100", "101", "110", "101", "10000", "11111"})
    @DisplayName("Начинается с 1 нечетной длины")
    void has0AtStartAndOddLenOr1AtStartAndEvenLen_ShouldReturnFalseFor1AtStartOddLenString(String string) {
        boolean matches = Task8.has0AtStartAndOddLenOr1AtStartAndEvenLen(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"12", "020", "102", "121", "120", "121", "10200", "11121"})
    @DisplayName("Строка содержит символы не из алфавита")
    void has0AtStartAndOddLenOr1AtStartAndEvenLen_ShouldReturnFalseForStringWithNoAlphabetChars(String string) {
        boolean matches = Task8.has0AtStartAndOddLenOr1AtStartAndEvenLen(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null строка для строк с кратными 3 нулями")
    void isZeroCountMultipleOf3_ShouldReturnFalseForNullString(String string) {
        boolean matches = Task8.isZeroCountMultipleOf3(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "000", "000000", "000000000", "010101", "101010", "1101010", "000111111", "11111000",
        "11101010101010111", "1010101", "1", "11", "111", "11101111011110111", "001111100111100"})
    @DisplayName("Строки с числом 0 кратным 3")
    void isZeroCountMultipleOf3_ShouldReturnTrueForValidString(String string) {
        boolean matches = Task8.isZeroCountMultipleOf3(string);

        assertThat(matches)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "00", "0000", "00000", "101", "010", "01010110", "1001"})
    @DisplayName("Строки с числом 0 не кратным 3")
    void isZeroCountMultipleOf3_ShouldReturnFalseForNotValidString(String string) {
        boolean matches = Task8.isZeroCountMultipleOf3(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"020101", "101020", "1201010", "000112111", "12111000", "11101010101010121", "1020101",
        "21"})
    @DisplayName("Строки с символами не из алфавита")
    void isZeroCountMultipleOf3_ShouldReturnFalseForStringContainsNotAlphabetChars(String string) {
        boolean matches = Task8.isZeroCountMultipleOf3(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null строка для строк кроме 11 и 111")
    void anyStringExcept11And111_ShouldReturnFalseForNullString(String string) {
        boolean matches = Task8.anyStringExcept11And111(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1", "0", "00", "000", "10", "01", "100", "101", "110", "1111"})
    @DisplayName("Строки кроме 11 и 111")
    void anyStringExcept11And111_ShouldReturnTrueForStringExcept111And11(String string) {
        boolean matches = Task8.anyStringExcept11And111(string);

        assertThat(matches)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "111"})
    @DisplayName("Строки 11 и 111")
    void anyStringExcept11And111_ShouldReturnFalseFor11Or111String(String string) {
        boolean matches = Task8.anyStringExcept11And111(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "22", "020", "12", "21", "120", "121", "120", "1211"})
    @DisplayName("Строки с символами не из алфавита")
    void anyStringExcept11And111_ShouldReturnFalseForStringContainsNotAlphabetChars(String string) {
        boolean matches = Task8.anyStringExcept11And111(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустая и Null строка для строк с 1 на нечетных позициях")
    void anyOddCharIs1_ShouldReturnFalseForNullAndEmptyString(String string) {
        boolean matches = Task8.anyOddCharIs1(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "11", "10", "101", "111", "10101", "11111", "10111", "11101"})
    @DisplayName("1 на нечетных позициях")
    void anyOddCharIs1_ShouldReturnTrueForValidString(String string) {
        boolean matches = Task8.anyOddCharIs1(string);

        assertThat(matches)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "01", "00", "010", "000", "01010", "00000", "01000", "00010"})
    @DisplayName("0 на нечетных позициях")
    void anyOddCharIs1_ShouldReturnFalseIfZeroOnOddPosition(String string) {
        boolean matches = Task8.anyOddCharIs1(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "12", "121", "12101", "11121", "12111"})
    @DisplayName("Строка с символами не из алфавита")
    void anyOddCharIs1_ShouldReturnFalseForNotAlphabetCharsInString(String string) {
        boolean matches = Task8.anyOddCharIs1(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустая и Null строка для строк с не менее 2 нулями и не более 1 единицы")
    void containsAtLeastTwo0AndNoMoreThanOne1_ShouldReturnFalseForNullAndEmptyString(String string) {
        boolean matches = Task8.containsAtLeastTwo0AndNoMoreThanOne1(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"00", "100", "010", "001", "000", "0000", "0001", "0010", "0100", "1000", "00000", "00001"})
    @DisplayName("Строка 2+ нулями и не более 1 единицей")
    void containsAtLeastTwo0AndNoMoreThanOne1_ShouldReturnTrueForValidString(String string) {
        boolean matches = Task8.containsAtLeastTwo0AndNoMoreThanOne1(string);

        assertThat(matches)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0011", "1100", "0110", "1001", "00011", "11000", "10001", "01010"})
    @DisplayName("Строка с более 1 единицей")
    void containsAtLeastTwo0AndNoMoreThanOne1_ShouldReturnFalseForStringsWith2OrMore1(String string) {
        boolean matches = Task8.containsAtLeastTwo0AndNoMoreThanOne1(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"01", "1", "10"})
    @DisplayName("Строка с менее 1 нулём")
    void containsAtLeastTwo0AndNoMoreThanOne1_ShouldReturnFalseForStringsWith1OrLessZeros(String string) {
        boolean matches = Task8.containsAtLeastTwo0AndNoMoreThanOne1(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"002", "2100", "0102", "0201", "200", "0200", "2001", "0210", "0120", "1200", "02021"})
    @DisplayName("Строка с символами не из алфавита")
    void containsAtLeastTwo0AndNoMoreThanOne1_ShouldReturnFalseForStringsWith1NoAlphabetChars(String string) {
        boolean matches = Task8.containsAtLeastTwo0AndNoMoreThanOne1(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null строка для последовательных 1")
    void noConsecutive1_ShouldReturnFalseForNullString(String string) {
        boolean matches = Task8.noConsecutive1(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "0", "00", "000", "0000", "101", "1", "1001", "0101", "1010", "00000", "10001", "10101",
        "01010", "00100", "000000", "101010", "010101", "100001"})
    @DisplayName("Строки без последовательных 1")
    void noConsecutive1_ShouldReturnTrueForValidString(String string) {
        boolean matches = Task8.noConsecutive1(string);

        assertThat(matches)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "111", "1111", "110", "011", "0110", "0011", "1100", "11111", "11100", "00111",
        "01110", "11011", "00110", "01100", "11110", "01111"})
    @DisplayName("Строки без последовательных 1")
    void noConsecutive1_ShouldReturnFalseForStringWithConsecutive1(String string) {
        boolean matches = Task8.noConsecutive1(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "02", "020", "0200", "121", "1201", "0121", "1210", "02000", "10021", "12101",
        "01210", "02100", "000020", "101012", "012101", "120001"})
    @DisplayName("Строка с символами не из алфавита")
    void noConsecutive1_ShouldReturnFalseForStringWithNoAlphabetChars(String string) {
        boolean matches = Task8.noConsecutive1(string);

        assertThat(matches)
            .isFalse();
    }
}
