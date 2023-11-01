package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    @ParameterizedTest
    @ValueSource(strings = {"~", "|", "a!b", "*a", "b@", "##", "aa$"})
    @DisplayName("Правильные пароли")
    void validatePassword_ShouldReturnTrueForValidPassword(String password) {
        boolean matches = Task4.validatePassword(password);

        assertThat(matches)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "abc", "A", "Ab", "AB", "a,b", "a.b"})
    @DisplayName("Неправильные пароли")
    void validatePassword_ShouldReturnFalseForNotValidPassword(String password) {
        boolean matches = Task4.validatePassword(password);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустая или null строка")
    void validatePassword_ShouldReturnFalseForEmptyOrNullPassword(String password) {
        boolean matches = Task4.validatePassword(password);

        assertThat(matches)
            .isFalse();
    }
}
