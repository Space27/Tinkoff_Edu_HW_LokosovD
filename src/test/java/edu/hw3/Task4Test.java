package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task4Test {

    @Test
    @DisplayName("Ноль")
    void convertToRoman_ShouldReturnEmptyStringForZero() {
        int number = 0;

        String convertedNumber = Task4.convertToRoman(number);

        assertThat(convertedNumber)
            .isEqualTo("");
    }

    @Test
    @DisplayName("Максимальное число")
    void convertToRoman_ShouldConvertMaxNumber() {
        int number = 3999;

        String convertedNumber = Task4.convertToRoman(number);

        assertThat(convertedNumber)
            .isEqualTo("MMMCMXCIX");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -1000, 4000, 10000})
    @DisplayName("Выход за границы римского числа")
    void convertToRoman_ShouldThrowExceptionForOutOfRomanRangeNumber(int number) {
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(number));
    }

    @ParameterizedTest
    @CsvSource({"1, I", "2, II", "4, IV", "5, V", "10, X", "12, XII", "16, XVI", "29, XXIX", "40, XL", "57, LVII",
        "95, XCV", "2494, MMCDXCIV", "999, CMXCIX", "1234, MCCXXXIV"})
    @DisplayName("Выход за границы римского числа")
    void convertToRoman_ShouldConvertNumberIfItIsValid(int number, String expected) {
        String convertedNumber = Task4.convertToRoman(number);

        assertThat(convertedNumber)
            .isEqualTo(expected);
    }
}