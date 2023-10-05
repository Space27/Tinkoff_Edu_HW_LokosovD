package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Числовая последовательность")
    void digitSeqStrFix() {
        String brokenStr = "214365";

        String fixedString = Task4.fixString(brokenStr);

        assertThat(fixedString)
            .isEqualTo("123456");
    }

    @Test
    @DisplayName("Нечетная длина строки")
    void oddLenStrFix() {
        String brokenStr = "migae";

        String fixedString = Task4.fixString(brokenStr);

        assertThat(fixedString)
            .isEqualTo("image");
    }

    @Test
    @DisplayName("Пустая строка")
    void emptyStrFix() {
        String brokenStr = "";

        String fixedString = Task4.fixString(brokenStr);

        assertThat(fixedString)
            .isEqualTo("");
    }

    @Test
    @DisplayName("Односимвольная строка")
    void oneCharStrFix() {
        String brokenStr = "a";

        String fixedString = Task4.fixString(brokenStr);

        assertThat(fixedString)
            .isEqualTo("a");
    }

    @Test
    @DisplayName("Двухсимвольная строка")
    void twoCharStrFix() {
        String brokenStr = "ba";

        String fixedString = Task4.fixString(brokenStr);

        assertThat(fixedString)
            .isEqualTo("ab");
    }

    @Test
    @DisplayName("Длинная строка")
    void longStrFix() {
        String brokenStr = "hTsii  s aimex dpus rtni.g";

        String fixedString = Task4.fixString(brokenStr);

        assertThat(fixedString)
            .isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("Строки нет")
    void nullStrFix() {
        String brokenStr = null;

        String fixedString = Task4.fixString(brokenStr);

        assertThat(fixedString)
            .isEqualTo(null);
    }
}
