package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @ParameterizedTest
    @CsvSource({"1378:59, 82739", "12:43, 763", "10:00, 600", "00:59, 59"})
    @DisplayName("Строка корректная")
    void minutesToSeconds_ShouldConvertIfDataIsValid(String input, int expected) {
        int convertedVideoLen = Task1.minutesToSeconds(input);

        assertThat(convertedVideoLen)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"00:00", "000:00"})
    @DisplayName("Нулевая длина")
    void minutesToSeconds_ShouldReturnZeroIfVideoLenIsZero(String input) {
        int convertedVideoLen = Task1.minutesToSeconds(input);

        assertThat(convertedVideoLen)
            .isEqualTo(0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"00:60", "12:61"})
    @DisplayName("Число минут превышает лимит")
    void minutesToSeconds_ShouldNotConvertIfSecondsAreHigherThanLimit(String input) {
        int convertedVideoLen = Task1.minutesToSeconds(input);

        assertThat(convertedVideoLen)
            .isEqualTo(-1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"01:01:01", "00:000"})
    @DisplayName("В строке лишние данные")
    void minutesToSeconds_ShouldNotConvertIfTooManyData(String input) {
        int convertedVideoLen = Task1.minutesToSeconds(input);

        assertThat(convertedVideoLen)
            .isEqualTo(-1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0101", "00:", ":00", "0:00", "00:0"})
    @DisplayName("Недостаточно данных в строке")
    void minutesToSeconds_ShouldNotConvertIfNotEnoughData(String input) {
        int convertedVideoLen = Task1.minutesToSeconds(input);

        assertThat(convertedVideoLen)
            .isEqualTo(-1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-01:00", "00:-01", "-03:-30"})
    @DisplayName("Отрицательные минуты или секунды")
    void minutesToSeconds_ShouldNotConvertNegativeNum(String input) {
        int convertedVideoLen = Task1.minutesToSeconds(input);

        assertThat(convertedVideoLen)
            .isEqualTo(-1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"ауцнн5g4554nlknkfj23", "минута01:00секунд"})
    @DisplayName("Мусор в строке")
    void minutesToSeconds_ShouldNotConvertIfGarbageInStr(String input) {
        int convertedVideoLen = Task1.minutesToSeconds(input);

        assertThat(convertedVideoLen)
            .isEqualTo(-1);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустая или null строка")
    void minutesToSeconds_ShouldNotConvertNullAndEmptyStr(String input) {
        int convertedVideoLen = Task1.minutesToSeconds(input);

        assertThat(convertedVideoLen)
            .isEqualTo(-1);
    }
}
