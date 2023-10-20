package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустая и null строка сортировка по возрастанию")
    void parseContacts_ShouldReturnSameStrIfItNullOfEmptyForAsc(String contact) {
        String[] contacts = {contact};

        String[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.ASC);

        assertThat(parsedContacts)
            .isEqualTo(new String[] {contact});
    }

    @ParameterizedTest
    @MethodSource("provideStrArrWithOnlyNames")
    @DisplayName("Только имена сортировка по возрастанию")
    void parseContacts_ShouldSortAscIfStringsIncludeOnlyNames(String[] contacts, String[] expected) {
        String[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.ASC);

        assertThat(parsedContacts)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideStrArrWithSurnames")
    @DisplayName("Строки содержат фамилию сортировка по возрастанию")
    void parseContacts_ShouldSortAscIfStringsIncludeSurnames(String[] contacts, String[] expected) {
        String[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.ASC);

        assertThat(parsedContacts)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideStrArrWithSomethingStrings")
    @DisplayName("Строки могут содержать только имена, либо имя с фамилией, либо быть пустыми по возрастанию")
    void parseContacts_ShouldSortAscSomethingStrings(String[] contacts, String[] expected) {
        String[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.ASC);

        assertThat(parsedContacts)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустая и null строка сортировка по убыванию")
    void parseContacts_ShouldReturnSameStrIfItNullOfEmptyForDesc(String contact) {
        String[] contacts = {contact};

        String[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.DESC);

        assertThat(parsedContacts)
            .isEqualTo(new String[] {contact});
    }

    @ParameterizedTest
    @MethodSource("provideStrArrWithOnlyNames")
    @DisplayName("Только имена сортировка по убыванию")
    void parseContacts_ShouldSortDescIfStringsIncludeOnlyNames(String[] contacts, String[] expected) {
        String[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.DESC);

        assertThat(parsedContacts)
            .isEqualTo(reverseArr(expected));
    }

    @ParameterizedTest
    @MethodSource("provideStrArrWithSurnames")
    @DisplayName("Строки содержат фамилию сортировка по убыванию")
    void parseContacts_ShouldSortDescIfStringsIncludeSurnames(String[] contacts, String[] expected) {
        String[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.DESC);

        assertThat(parsedContacts)
            .isEqualTo(reverseArr(expected));
    }

    @ParameterizedTest
    @MethodSource("provideStrArrWithSomethingStrings")
    @DisplayName("Строки могут содержать только имена, либо имя с фамилией, либо быть пустыми по убыванию")
    void parseContacts_ShouldSortDescSomethingStrings(String[] contacts, String[] expected) {
        String[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.DESC);

        assertThat(parsedContacts)
            .isEqualTo(reverseArr(expected));
    }

    private static String[] reverseArr(String[] arr) {
        String[] resArr = new String[arr.length];

        for (int i = 0; i <= arr.length / 2; ++i) {
            resArr[i] = arr[arr.length - i - 1];
            resArr[arr.length - i - 1] = arr[i];
        }

        return resArr;
    }

    private static Stream<Arguments> provideStrArrWithOnlyNames() {
        return Stream.of(
            Arguments.of(
                new String[] {"james"},
                new String[] {"james"}
            ),
            Arguments.of(
                new String[] {"james", "angus"},
                new String[] {"angus", "james"}
            ),
            Arguments.of(
                new String[] {"james", "angus", "vinnie"},
                new String[] {"angus", "james", "vinnie"}
            )
        );
    }

    private static Stream<Arguments> provideStrArrWithSurnames() {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                new String[] {"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"}
            ),
            Arguments.of(
                new String[] {"Angus Young", "James Hetfield"},
                new String[] {"James Hetfield", "Angus Young"}
            ),
            Arguments.of(
                new String[] {"Paul Erdos", "Carl Gauss", "Leonhard Euler"},
                new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"}
            )
        );
    }

    private static Stream<Arguments> provideStrArrWithSomethingStrings() {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas", "David", "Rene Descartes"},
                new String[] {"David", "Rene Descartes", "John Locke", "Thomas"}
            ),
            Arguments.of(
                new String[] {"", "James Hetfield", "Dave"},
                new String[] {"", "Dave", "James Hetfield"}
            ),
            Arguments.of(
                new String[] {"Paul", "Carl Gauss", null},
                new String[] {null, "Carl Gauss", "Paul"}
            ),
            Arguments.of(
                new String[] {null, "", null},
                new String[] {null, "", null}
            )
        );
    }
}
