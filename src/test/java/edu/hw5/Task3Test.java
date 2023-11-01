package edu.hw5;

import edu.hw5.Task3.CommonDateProcessor;
import edu.hw5.Task3.DaysAgoProcessor;
import edu.hw5.Task3.FormatProcessor;
import edu.hw5.Task3.ReverseDateProcessor;
import edu.hw5.Task3.ReverseDateProcessorWithShortedYear;
import edu.hw5.Task3.TodayProcessor;
import edu.hw5.Task3.TomorrowProcessor;
import edu.hw5.Task3.YesterdayProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.time.LocalDate;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    private static FormatProcessor getChainOfFormatProcessor() {
        FormatProcessor daysAgoProcessor = new DaysAgoProcessor(null);
        FormatProcessor yesterdayProcessor = new YesterdayProcessor(daysAgoProcessor);
        FormatProcessor todayProcessor = new TodayProcessor(yesterdayProcessor);
        FormatProcessor tomorrowProcessor = new TomorrowProcessor(todayProcessor);
        FormatProcessor reverseDateProcessor = new ReverseDateProcessor(tomorrowProcessor);
        FormatProcessor reverseDateProcessorWithShortedYear =
            new ReverseDateProcessorWithShortedYear(reverseDateProcessor);
        return new CommonDateProcessor(reverseDateProcessorWithShortedYear);
    }

    @ParameterizedTest
    @MethodSource("provideValidDateFormats")
    @DisplayName("Корректные форматы дат")
    void processTheDate_ShouldProcessValidDateFormats(String date, LocalDate localDate) {
        FormatProcessor formatProcessor = getChainOfFormatProcessor();

        assertThat(formatProcessor.processTheDate(date))
            .isNotEmpty();
        assertThat(formatProcessor.processTheDate(date).orElse(localDate).getDayOfYear())
            .isEqualTo(localDate.getDayOfYear());
        assertThat(formatProcessor.processTheDate(date).orElse(localDate).getYear())
            .isEqualTo(localDate.getYear());
    }

    @ParameterizedTest
    @ValueSource(strings = {"20-11-10", "date", "1-3-1975", "1 days ago", "0 days ago", "-1 days ago"})
    @DisplayName("Некорректные форматы дат")
    void processTheDate_ShouldReturnEmptyOptionalForNotValidDateFormat(String date) {
        FormatProcessor formatProcessor = getChainOfFormatProcessor();

        assertThat(formatProcessor.processTheDate(date))
            .isEmpty();
    }

    private static Stream<Arguments> provideValidDateFormats() {
        return Stream.of(
            Arguments.of("2023-1-2", LocalDate.of(2023, 1, 2)),
            Arguments.of("2023-01-02", LocalDate.of(2023, 1, 2)),
            Arguments.of("2023-10-2", LocalDate.of(2023, 10, 2)),
            Arguments.of("2020-10-11", LocalDate.of(2020, 10, 11)),
            Arguments.of("1/3/1975", LocalDate.of(1975, 3, 1)),
            Arguments.of("10/4/20", LocalDate.of(2020, 4, 10)),
            Arguments.of("today", LocalDate.now()),
            Arguments.of("yesterday", LocalDate.now().minusDays(1)),
            Arguments.of("tomorrow", LocalDate.now().plusDays(1)),
            Arguments.of("1 day ago", LocalDate.now().minusDays(1)),
            Arguments.of("105 days ago", LocalDate.now().minusDays(105))
        );
    }
}
