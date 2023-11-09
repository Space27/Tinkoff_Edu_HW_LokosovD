package edu.hw3;

import edu.hw3.Task5.Contact;
import edu.hw3.Task5.Task5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class Task5Test {

    @ParameterizedTest
    @NullSource
    @DisplayName("Null массив по возрастанию")
    void parseContacts_shouldReturnEmptyArrayForNullArrayForAsc(String[] contacts) {
        Contact[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.ASC);

        assertThat(parsedContacts)
            .isEmpty();
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null массив по убыванию")
    void parseContacts_shouldReturnEmptyArrayForNullArrayForDesc(String[] contacts) {
        Contact[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.DESC);

        assertThat(parsedContacts)
            .isEmpty();
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("Пустая строка сортировка по возрастанию")
    void parseContacts_shouldReturnSameStrIfItEmptyForAsc(String contact) {
        String[] contacts = {contact};

        Contact[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.ASC);

        assertThat(parsedContacts)
            .isEqualTo(new Contact[] {new Contact(contact)});
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null строка сортировка по возрастанию")
    void parseContacts_shouldReturnEmptyArrIfItNullForAsc(String contact) {
        String[] contacts = {contact};

        Contact[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.ASC);

        assertThat(parsedContacts)
            .isEmpty();
    }

    @ParameterizedTest
    @MethodSource("provideStrArrWithOnlyNames")
    @DisplayName("Только имена сортировка по возрастанию")
    void parseContacts_shouldSortAscIfStringsIncludeOnlyNames(String[] contacts, Contact[] expected) {
        Contact[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.ASC);

        assertThat(parsedContacts)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideStrArrWithSurnames")
    @DisplayName("Строки содержат фамилию сортировка по возрастанию")
    void parseContacts_shouldSortAscIfStringsIncludeSurnames(String[] contacts, Contact[] expected) {
        Contact[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.ASC);

        assertThat(parsedContacts)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideStrArrWithSomethingStrings")
    @DisplayName("Строки могут содержать только имена, либо имя с фамилией, либо быть пустыми по возрастанию")
    void parseContacts_shouldSortAscSomethingStrings(String[] contacts, Contact[] expected) {
        Contact[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.ASC);

        assertThat(parsedContacts)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("Пустая строка сортировка по убыванию")
    void parseContacts_shouldReturnSameStrIfItEmptyForDesc(String contact) {
        String[] contacts = {contact};

        Contact[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.DESC);

        assertThat(parsedContacts)
            .isEqualTo(new Contact[] {new Contact(contact)});
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null строка сортировка по убыванию")
    void parseContacts_shouldReturnEmptyArrIfItNullForDesc(String contact) {
        String[] contacts = {contact};

        Contact[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.DESC);

        assertThat(parsedContacts)
            .isEmpty();
    }

    @ParameterizedTest
    @MethodSource("provideStrArrWithOnlyNames")
    @DisplayName("Только имена сортировка по убыванию")
    void parseContacts_shouldSortDescIfStringsIncludeOnlyNames(String[] contacts, Contact[] expected) {
        Contact[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.DESC);

        assertThat(parsedContacts)
            .isEqualTo(reverseArr(expected));
    }

    @ParameterizedTest
    @MethodSource("provideStrArrWithSurnames")
    @DisplayName("Строки содержат фамилию сортировка по убыванию")
    void parseContacts_shouldSortDescIfStringsIncludeSurnames(String[] contacts, Contact[] expected) {
        Contact[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.DESC);

        assertThat(parsedContacts)
            .isEqualTo(reverseArr(expected));
    }

    @ParameterizedTest
    @MethodSource("provideStrArrWithSomethingStrings")
    @DisplayName("Строки могут содержать только имена, либо имя с фамилией, либо быть пустыми по убыванию")
    void parseContacts_shouldSortDescSomethingStrings(String[] contacts, Contact[] expected) {
        Contact[] parsedContacts = Task5.parseContacts(contacts, Task5.Order.DESC);

        assertThat(parsedContacts)
            .isEqualTo(reverseArr(expected));
    }

    private static Contact[] reverseArr(Contact[] arr) {
        Contact[] resArr = new Contact[arr.length];

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
                new Contact[] {new Contact("james")}
            ),
            Arguments.of(
                new String[] {"james", "angus"},
                new Contact[] {new Contact("angus"), new Contact("james")}
            ),
            Arguments.of(
                new String[] {"james", "angus", "vinnie"},
                new Contact[] {new Contact("angus"), new Contact("james"), new Contact("vinnie")}
            )
        );
    }

    private static Stream<Arguments> provideStrArrWithSurnames() {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                new Contact[] {new Contact("Thomas Aquinas"), new Contact("Rene Descartes"),
                    new Contact("David Hume"), new Contact("John Locke")}
            ),
            Arguments.of(
                new String[] {"Angus Young", "James Hetfield"},
                new Contact[] {new Contact("James Hetfield"), new Contact("Angus Young")}
            ),
            Arguments.of(
                new String[] {"Paul Erdos", "Carl Gauss", "Leonhard Euler"},
                new Contact[] {new Contact("Paul Erdos"), new Contact("Leonhard Euler"), new Contact("Carl Gauss")}
            )
        );
    }

    private static Stream<Arguments> provideStrArrWithSomethingStrings() {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas", "David", "Rene Descartes"},
                new Contact[] {new Contact("David"), new Contact("Rene Descartes"), new Contact("John Locke"),
                    new Contact("Thomas")}
            ),
            Arguments.of(
                new String[] {"", "James Hetfield", "Dave"},
                new Contact[] {new Contact(""), new Contact("Dave"), new Contact("James Hetfield")}
            ),
            Arguments.of(
                new String[] {"Paul", "Carl Gauss", null},
                new Contact[] {new Contact("Carl Gauss"), new Contact("Paul")}
            ),
            Arguments.of(
                new String[] {null, "", null},
                new Contact[] {new Contact("")}
            )
        );
    }
}
