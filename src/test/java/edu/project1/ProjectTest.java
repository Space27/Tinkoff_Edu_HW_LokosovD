package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProjectTest {

    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "abc", "abcdef"})
    @DisplayName("Длина маски слова")
    void WordHandler_ShouldCreatMaskSameLenWithWord(String word) {
        WordHandler wordHandler = new WordHandler(word);

        assertThat(wordHandler.getWordMask().length())
            .isEqualTo(word.length());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "abc", "abcdef"})
    @DisplayName("Длина маски слова")
    void WordHandler_ShouldCreatMaskWithOnlyAsterisk(String word) {
        WordHandler wordHandler = new WordHandler(word);

        assertThat(wordHandler.getWordMask())
            .isEqualTo("*".repeat(word.length()));
    }

    @ParameterizedTest
    @CsvSource({"a, a, a", "ab, a, a*", "ab, b, *b", "abc, b, *b*", "abac, a, a*a*"})
    @DisplayName("Длина маски слова")
    void WordHandler_ShouldOpenLetters(String word, char letter, String expectedMask) {
        WordHandler wordHandler = new WordHandler(word);

        wordHandler.openLetter(letter);

        assertThat(wordHandler.getWordMask())
            .isEqualTo(expectedMask);
    }

    @Test
    @DisplayName("Слово маленькой длины")
    void run_ShouldThrowExceptIfWordLenIsLenThan3() {
        String word = "ab";

        ConsoleHangman consoleHangman = new ConsoleHangman();

        assertThrows(IllegalArgumentException.class, () -> consoleHangman.run(word));
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("Пустое слово")
    void run_ShouldThrowExceptIfWordLenIsLenThan3(String word) {
        ConsoleHangman consoleHangman = new ConsoleHangman();

        assertThrows(IllegalArgumentException.class, () -> consoleHangman.run(word));
    }

    @Test
    @DisplayName("Превышено число попыток")
    void GameSession_ShouldReturnTrueForLoseIfAttemptsAreEqualMax() {
        WordHandler wordHandler = new WordHandler("cat");
        GameSession gameSession = new GameSession(wordHandler);

        gameSession.guess('q');
        gameSession.guess('w');
        gameSession.guess('e');
        gameSession.guess('r');
        gameSession.guess('y');
        gameSession.guess('u');
        gameSession.guess('i');

        assertThat(gameSession.isLose())
            .isTrue();
    }

    @Test
    @DisplayName("Слово угадано")
    void GameSession_ShouldReturnTrueForWinIfWordWasGuessed() {
        WordHandler wordHandler = new WordHandler("cat");
        GameSession gameSession = new GameSession(wordHandler);

        gameSession.guess('c');
        gameSession.guess('a');
        gameSession.guess('t');

        assertThat(gameSession.isWin())
            .isTrue();
    }

    @Test
    @DisplayName("Выход из игры")
    void GameSession_ShouldReturnFalseForInGameIfInputIsQuitChar() {
        WordHandler wordHandler = new WordHandler("cat");
        GameSession gameSession = new GameSession(wordHandler);

        gameSession.guess('0');

        assertThat(gameSession.inGame())
            .isFalse();
    }

    @Test
    @DisplayName("Не угадывание буквы")
    void GameSession_ShouldReturnNotGuessedMessageIfLetterIncorrect() {
        WordHandler wordHandler = new WordHandler("cat");
        GameSession gameSession = new GameSession(wordHandler);

        String message = gameSession.guess('q');

        assertThat(message)
            .isEqualTo(MessageManager.notGuessed(1, 7));
    }

    @Test
    @DisplayName("Не угадывание буквы")
    void GameSession_ShouldReturnGuessedMessageIfLetterCorrect() {
        WordHandler wordHandler = new WordHandler("cat");
        GameSession gameSession = new GameSession(wordHandler);

        String message = gameSession.guess('c');

        assertThat(message)
            .isEqualTo(MessageManager.guessed());
    }

    @Test
    @DisplayName("Повторение буквы")
    void GameSession_ShouldReturnRepeatMessageIfLetterWasRepeated() {
        WordHandler wordHandler = new WordHandler("cat");
        GameSession gameSession = new GameSession(wordHandler);

        gameSession.guess('c');
        String message = gameSession.guess('c');

        assertThat(message)
            .isEqualTo(MessageManager.repeat());
    }
}
