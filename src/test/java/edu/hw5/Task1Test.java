package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Пустой или null список")
    void getAvgSessionTime_ShouldReturnZeroForEmptyOrNullList(List<String> list) {
        String avgTime = Task1.getAvgSessionTime(list);

        assertThat(avgTime)
            .isEqualTo("0ч 0м");
    }

    @ParameterizedTest
    @MethodSource("provideNotValidPeriods")
    @DisplayName("Периоды с ошибками")
    void getAvgSessionTime_ShouldReturnThrowExceptionForNotValidPeriods(List<String> list) {
        assertThrows(IllegalArgumentException.class, () -> Task1.getAvgSessionTime(list));
    }

    @ParameterizedTest
    @MethodSource("provideOneSizeList")
    @DisplayName("Список из 1 периода")
    void getAvgSessionTime_ShouldReturnPeriodTimeForOneSizeList(List<String> list, String expected) {
        String avgTime = Task1.getAvgSessionTime(list);

        assertThat(avgTime)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideValidList")
    @DisplayName("Список из валидных периодов")
    void getAvgSessionTime_ShouldReturnPeriodTimeForValidList(List<String> list, String expected) {
        String avgTime = Task1.getAvgSessionTime(list);

        assertThat(avgTime)
            .isEqualTo(expected);
    }

    private static Stream<Arguments> provideNotValidPeriods() {
        return Stream.of(
            Arguments.of(List.of("2022-03-12, 20:20 - 2022-03-12, 20:19")),
            Arguments.of(List.of("2022-04-02, 21:30 - 2022-04-01, 01:20")),
            Arguments.of(List.of("2022-04-01, 21:30")),
            Arguments.of(List.of("2022-04-01, 21:30 - ")),
            Arguments.of(List.of("")),
            Arguments.of(List.of("2022-04-02 - 2022-04-03")),
            Arguments.of(List.of("20:20 - 20:19")),
            Arguments.of(List.of(" - "))
        );
    }

    private static Stream<Arguments> provideOneSizeList() {
        return Stream.of(
            Arguments.of(List.of("2022-03-12, 20:20 - 2022-03-12, 23:50"), "3ч 30м"),
            Arguments.of(List.of("2022-04-01, 21:30 - 2022-04-02, 01:20"), "3ч 50м"),
            Arguments.of(List.of("2022-04-01, 21:30 - 2022-04-01, 21:30"), "0ч 0м")
        );
    }

    private static Stream<Arguments> provideValidList() {
        return Stream.of(
            Arguments.of(List.of(
                "2022-03-12, 20:20 - 2022-03-12, 23:50",
                "2022-04-01, 21:30 - 2022-04-02, 01:20"
            ), "3ч 40м"),
            Arguments.of(List.of(
                "2022-03-12, 20:20 - 2022-03-12, 20:20",
                "2022-03-13, 20:20 - 2022-03-13, 20:20",
                "2022-04-01, 21:30 - 2022-04-02, 01:20"
            ), "1ч 16м")
        );
    }
}
