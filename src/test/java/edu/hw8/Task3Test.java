package edu.hw8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {

    @Test
    @DisplayName("Пароли из одного символа")
    void decipherUserPasswords_shouldEncryptOneCharPasswords() {
        List<String> lines = List.of(
            "wasya cfcd208495d565ef66e7dff9f98764da",
            "iuwanya 0cc175b9c0f1b6a831c399e269772661",
            "petreuvich 7fc56270e7a70fa81a5935b72eacbe29"
        );
        Map<String, String> expected = Map.of(
            "wasya", "0",
            "iuwanya", "a",
            "petreuvich", "A"
        );

        Map<String, String> result = Task3.decipherUserPasswords(lines);

        assertThat(result)
            .containsExactlyInAnyOrderEntriesOf(expected);
    }

    @Test
    @DisplayName("Пароли из двух-четырех символов")
    void decipherUserPasswords_shouldEncryptSeveralCharPasswords() {
        List<String> lines = List.of(
            "wasya b4b147bc522828731f1a016bfa72c073",
            "iuwanya 25ed1bcb423b0b7200f485fc5ff71c8e",
            "petreuvich 4cd08a5a9da1312eb45c897a1452521c",
            "okto f3abb86bd34cf4d52698f14c0da1dc60",
            "nioh 4a7d1ed414474e4033ac29ccb8653d9b",
            "witcher 8b13cfc1eff5001176035dd16e2cf54a"
        );
        Map<String, String> expected = Map.of(
            "wasya", "00",
            "iuwanya", "zz",
            "petreuvich", "0aA",
            "okto", "zzz",
            "nioh", "0000",
            "witcher", "1Aaz"
        );

        Map<String, String> result = Task3.decipherUserPasswords(lines);

        assertThat(result)
            .containsExactlyInAnyOrderEntriesOf(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустой или null список")
    void decipherUserPasswords_shouldReturnEmptyMapForEmptyOrNullList(List<String> list) {
        Map<String, String> result = Task3.decipherUserPasswords(list);

        assertThat(result)
            .isEmpty();
    }

    @Test
    @DisplayName("Пустой или null список")
    void decipherUserPasswords_shouldReturnEmptyMapForIncorrectStrings() {
        List<String> lines = List.of(
            "b4b147bc522828731f1a016bfa72c073",
            "iuwanya",
            "petreuvich 4cd08a5a9da1312eb45c897a1452521c petreuvich",
            "f3abb86bd34cf4d52698f14c0da1dc60 okto f3abb86bd34cf4d52698f14c0da1dc60",
            "nioh ",
            ""
        );

        Map<String, String> result = Task3.decipherUserPasswords(lines);

        assertThat(result)
            .isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустой или null список для многопотока")
    void decipherUserPasswordsInParallel_shouldReturnEmptyMapForEmptyOrNullList(List<String> list) {
        Map<String, String> result = Task3.decipherUserPasswordsInParallel(list);

        assertThat(result)
            .isEmpty();
    }

    @Test
    @DisplayName("Пустой или null список для многопотока")
    void decipherUserPasswordsInParallel_shouldReturnEmptyMapForIncorrectStrings() {
        List<String> lines = List.of(
            "b4b147bc522828731f1a016bfa72c073",
            "iuwanya",
            "petreuvich 4cd08a5a9da1312eb45c897a1452521c petreuvich",
            "f3abb86bd34cf4d52698f14c0da1dc60 okto f3abb86bd34cf4d52698f14c0da1dc60",
            "nioh ",
            ""
        );

        Map<String, String> result = Task3.decipherUserPasswordsInParallel(lines);

        assertThat(result)
            .isEmpty();
    }

    @Test
    @DisplayName("Пароли из одного символа для многопотока")
    void decipherUserPasswordsInParallel_shouldEncryptOneCharPasswords() {
        List<String> lines = List.of(
            "wasya cfcd208495d565ef66e7dff9f98764da",
            "iuwanya 0cc175b9c0f1b6a831c399e269772661",
            "petreuvich 7fc56270e7a70fa81a5935b72eacbe29"
        );
        Map<String, String> expected = Map.of(
            "wasya", "0",
            "iuwanya", "a",
            "petreuvich", "A"
        );

        Map<String, String> result = Task3.decipherUserPasswordsInParallel(lines);

        assertThat(result)
            .containsExactlyInAnyOrderEntriesOf(expected);
    }

    @Test
    @DisplayName("Пароли из двух-четырех символов для многопотока")
    void decipherUserPasswordsInParallel_shouldEncryptSeveralCharPasswords() {
        List<String> lines = List.of(
            "wasya b4b147bc522828731f1a016bfa72c073",
            "iuwanya 25ed1bcb423b0b7200f485fc5ff71c8e",
            "petreuvich 4cd08a5a9da1312eb45c897a1452521c",
            "okto f3abb86bd34cf4d52698f14c0da1dc60",
            "nioh 4a7d1ed414474e4033ac29ccb8653d9b",
            "witcher 8b13cfc1eff5001176035dd16e2cf54a"
        );
        Map<String, String> expected = Map.of(
            "wasya", "00",
            "iuwanya", "zz",
            "petreuvich", "0aA",
            "okto", "zzz",
            "nioh", "0000",
            "witcher", "1Aaz"
        );

        Map<String, String> result = Task3.decipherUserPasswordsInParallel(lines);

        assertThat(result)
            .containsExactlyInAnyOrderEntriesOf(expected);
    }
}
