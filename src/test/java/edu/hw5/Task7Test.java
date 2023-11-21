package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {

    @ParameterizedTest
    @ValueSource(strings = {"000", "110", "100", "010", "0000", "0001", "1101"})
    @DisplayName("Строки содержат 3 символа, причем 3-й - 0")
    void containsAtLeast3CharsAndThirdCharIs0_ShouldReturnTrueForValidString(String string) {
        boolean matches = Task7.containsAtLeast3CharsAndThirdCharIs0(string);

        assertThat(matches)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"00", "11", "10", "01", "0", "1"})
    @DisplayName("Строки содержат меньше 3 символов")
    void containsAtLeast3CharsAndThirdCharIs0_ShouldReturnFalseForTooShortStrings(String string) {
        boolean matches = Task7.containsAtLeast3CharsAndThirdCharIs0(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"001", "111", "101", "011", "1111", "0010"})
    @DisplayName("Третий символ - 1")
    void containsAtLeast3CharsAndThirdCharIs0_ShouldReturnFalseIfThirdCharIs1(String string) {
        boolean matches = Task7.containsAtLeast3CharsAndThirdCharIs0(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"200", "220", "200", "020", "0200", "0002", "1201"})
    @DisplayName("Есть символы не из алфавита")
    void containsAtLeast3CharsAndThirdCharIs0_ShouldReturnFalseIfStringContainNo0Or1(String string) {
        boolean matches = Task7.containsAtLeast3CharsAndThirdCharIs0(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустая или null строка")
    void containsAtLeast3CharsAndThirdCharIs0_ShouldReturnFalseForEmptyOfNullString(String string) {
        boolean matches = Task7.containsAtLeast3CharsAndThirdCharIs0(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "101", "111", "00", "010", "000", "1001", "1011", "1101", "1111", "0000", "0110",
        "0010", "0100", "0", "1", ""})
    @DisplayName("Строка начинается и кончается одним символом")
    void startsAndEndsSameTheSameChar_ShouldReturnTrueForValidString(String string) {
        boolean matches = Task7.startsAndEndsSameTheSameChar(string);

        assertThat(matches)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"10", "01", "011", "100", "110", "001", "0001", "1000"})
    @DisplayName("Строка начинается и кончается разными символами")
    void startsAndEndsSameTheSameChar_ShouldReturnFalseIfStartAndEndCharsAreDifferent(String string) {
        boolean matches = Task7.startsAndEndsSameTheSameChar(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"121", "121", "020", "020", "1201", "1211", "1121", "1121", "0200", "0210",
        "0210", "0120", "2"})
    @DisplayName("Строка содержит символы не из алфавита")
    void startsAndEndsSameTheSameChar_ShouldReturnFalseIfStringsHasNotAlphabetChars(String string) {
        boolean matches = Task7.startsAndEndsSameTheSameChar(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null строка")
    void startsAndEndsSameTheSameChar_ShouldReturnFalseForNullString(String string) {
        boolean matches = Task7.startsAndEndsSameTheSameChar(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "01", "10", "11", "00", "000", "111", "001", "010", "011", "100"})
    @DisplayName("Строка длиннее 1, но меньше 3")
    void lenIsMoreThan1ButLowerThan3_ShouldReturnTrueForValidString(String string) {
        boolean matches = Task7.lenIsMoreThan1ButLowerThan3(string);

        assertThat(matches)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1111", "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001"})
    @DisplayName("Строка длиннее 3")
    void lenIsMoreThan1ButLowerThan3_ShouldReturnTrueForNotValidString(String string) {
        boolean matches = Task7.lenIsMoreThan1ButLowerThan3(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "21", "20", "12", "02", "020", "121", "021", "210", "012", "120"})
    @DisplayName("Строка содержит символы не из алфавита")
    void lenIsMoreThan1ButLowerThan3_ShouldReturnTrueIfStringHasNoAlphabetChars(String string) {
        boolean matches = Task7.lenIsMoreThan1ButLowerThan3(string);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустая или null строка")
    void lenIsMoreThan1ButLowerThan3_ShouldReturnFalseForEmptyOfNullString(String string) {
        boolean matches = Task7.lenIsMoreThan1ButLowerThan3(string);

        assertThat(matches)
            .isFalse();
    }
}
