package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @ParameterizedTest
    @CsvSource({"ba, ab", "ab, ba", "214365, 123456", "hTsii  s aimex dpus rtni.g, This is a mixed up string."})
    @DisplayName("Строка с четной длиной")
    void fixString_ShouldFixEvenLenStr(String input, String expected) {
        String fixedString = Task4.fixString(input);

        assertThat(fixedString)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"a, a", "ehllo, hello", "hello, ehllo", "migae, image", "123, 213"})
    @DisplayName("Строка с нечетной длиной")
    void fixString_ShouldFixOddLenStr(String input, String expected) {
        String fixedString = Task4.fixString(input);

        assertThat(fixedString)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("Пустая строка")
    void fixString_ShouldFixEmptyStr(String input) {
        String fixedString = Task4.fixString(input);

        assertThat(fixedString)
            .isEmpty();
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null строка")
    void fixString_ShouldNotFixNull(String input) {
        String fixedString = Task4.fixString(input);

        assertThat(fixedString)
            .isNull();
    }
}
