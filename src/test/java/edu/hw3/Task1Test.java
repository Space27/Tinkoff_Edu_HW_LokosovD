package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустая и null строки")
    void atbash_shouldReturnStringIfItNullOfEmpty(String string) {
        String encryptedString = Task1.atbash(string);

        assertThat(encryptedString)
            .isEqualTo(string);
    }

    @ParameterizedTest
    @CsvSource({"a, z", "b, y", "c, x", "z, a", "y, b", "x, c", "m, n", "n, m", "A, Z", "B, Y", "C, X", "Z, A", "Y, B",
        "X, C", "M, N", "N, M"})
    @DisplayName("Один латинский символ")
    void atbash_shouldEncryptLetterForOneLatinLetter(String string, String expectedString) {
        String encryptedString = Task1.atbash(string);

        assertThat(encryptedString)
            .isEqualTo(expectedString);
    }

    @ParameterizedTest
    @ValueSource(strings = {".", " ", "-", "<", "@", "Ц", "ц", "1", "0", "!", "?", "%", "$", "/"})
    @DisplayName("Один не латинский символ")
    void atbash_shouldNotEncryptNotLatinAlphas(String string) {
        String encryptedString = Task1.atbash(string);

        assertThat(encryptedString)
            .isEqualTo(string);
    }

    @ParameterizedTest
    @CsvSource({"Hello, Svool", "world, dliow", "understand, fmwvihgzmw", "programmers, kiltiznnvih"})
    @DisplayName("Длинная строка, в которой только латинские буквы")
    void atbash_shouldEncryptLongStringsWithOnlyLatinAlphas(String string, String expectedString) {
        String encryptedString = Task1.atbash(string);

        assertThat(encryptedString)
            .isEqualTo(expectedString);
    }

    @ParameterizedTest
    @ValueSource(strings = {".- ", "   ", "---_---", "<><>", "@@#", "911", "12564..///!!@#"})
    @DisplayName("Длинная строка, в которой нет латинских букв")
    void atbash_shouldNotEncryptLongStringsWithNotOnlyLatinAlphas(String string) {
        String encryptedString = Task1.atbash(string);

        assertThat(encryptedString)
            .isEqualTo(string);
    }

    @ParameterizedTest
    @CsvSource({"Hello world!, Svool dliow!",
        "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler, Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi",
        "Papa Het - Metallica HEAD, Kzkz Svg - Nvgzoorxz SVZW"})
    @DisplayName("Длинная строка, в которой содержатся латинские и не латинские символы")
    void atbash_shouldEncryptLongMixedStrings(String string, String expectedString) {
        String encryptedString = Task1.atbash(string);

        assertThat(encryptedString)
            .isEqualTo(expectedString);
    }
}
