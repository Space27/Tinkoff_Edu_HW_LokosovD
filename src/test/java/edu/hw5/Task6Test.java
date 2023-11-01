package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {

    @ParameterizedTest
    @CsvSource({"abc, achfdbaabgabcaabg", "abd, abcde", "qeyop, qwertyuop", ",", "qey, qwerty", "b, abc", "a, a",
        "-, -", "abc, .-a313!@b4424c422--+"})
    @DisplayName("Строка является подпоследовательностью")
    void isSubSequence_ShouldReturnTrueIfStringIsSubSequence(String sub, String string) {
        boolean isSubSequence = Task6.isSubSequence(sub, string);

        assertThat(isSubSequence)
            .isTrue();
    }

    @ParameterizedTest
    @CsvSource({"acb, abcde", "a, b", "ab, ba"})
    @DisplayName("Строка не является подпоследовательностью")
    void isSubSequence_ShouldReturnFalseIfStringIsNotSubSequence(String sub, String string) {
        boolean isSubSequence = Task6.isSubSequence(sub, string);

        assertThat(isSubSequence)
            .isFalse();
    }
}
