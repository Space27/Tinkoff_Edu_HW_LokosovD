package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {

    @ParameterizedTest
    @ValueSource(strings = {"А123ВЕ777", "О777ОО177", "А123ВЕ77", "A123BE77"})
    @DisplayName("Правильные номерные знаки")
    void validateRegistrationMark_ShouldReturnTrueForValidRegistrationMark(String registrationMark) {
        boolean matches = Task5.validateRegistrationMark(registrationMark);

        assertThat(matches)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "123АВЕ777", "А123ВЕ7777", "А123ВЕ7", "1АВЕ23АВЕ", "А123ВЕ", "123ВЕ777", "А12ВЕ777",
        "А123В777", "АВЕ777", "А1ВЕ777", "А123ВЕE777", "АB123ВЕ777", "А123ВЕ777B"})
    @DisplayName("Неправильные номерные знаки")
    void validateRegistrationMark_ShouldReturnFalseForNotValidRegistrationMark(String registrationMark) {
        boolean matches = Task5.validateRegistrationMark(registrationMark);

        assertThat(matches)
            .isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустая или null строка")
    void validatePassword_ShouldReturnFalseForEmptyOrNullRegistrationMark(String RegistrationMark) {
        boolean matches = Task5.validateRegistrationMark(RegistrationMark);

        assertThat(matches)
            .isFalse();
    }
}
