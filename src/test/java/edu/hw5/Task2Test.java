package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @ParameterizedTest
    @MethodSource("provide13thFridays")
    @DisplayName("Все пятницы 13-го в году")
    void get13thFridaysInYear_ShouldReturnAll13thFridaysInYear(int year, List<LocalDate> expected) {
        List<LocalDate> resultList = Task2.get13thFridaysInYear(year);

        assertThat(resultList)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideDateAndNearFriday13th")
    @DisplayName("Следующая пятница 13-го")
    void getNext13thFriday_ShouldReturnNext13thFriday(LocalDate date, LocalDate expected) {
        LocalDate resultDate = Task2.getNext13thFriday(date);

        assertThat(resultDate)
            .isEqualTo(expected);
    }

    private static Stream<Arguments> provide13thFridays() {
        return Stream.of(
            Arguments.of(
                1925,
                List.of(
                    LocalDate.of(1925, 2, 13),
                    LocalDate.of(1925, 3, 13),
                    LocalDate.of(1925, 11, 13)
                )
            ),
            Arguments.of(
                2024,
                List.of(
                    LocalDate.of(2024, 9, 13),
                    LocalDate.of(2024, 12, 13)
                )
            ),
            Arguments.of(
                2023,
                List.of(
                    LocalDate.of(2023, 1, 13),
                    LocalDate.of(2023, 10, 13)
                )
            )
        );
    }

    private static Stream<Arguments> provideDateAndNearFriday13th() {
        return Stream.of(
            Arguments.of(LocalDate.ofYearDay(2023, 5), LocalDate.of(2023, 1, 13)),
            Arguments.of(LocalDate.of(2023, 1, 13), LocalDate.of(2023, 10, 13)),
            Arguments.of(LocalDate.of(2023, 10, 13), LocalDate.of(2024, 9, 13))
        );
    }
}
