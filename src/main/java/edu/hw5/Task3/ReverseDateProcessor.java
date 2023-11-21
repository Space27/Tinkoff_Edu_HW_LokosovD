package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class ReverseDateProcessor extends FormatProcessor {

    public ReverseDateProcessor(FormatProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public Optional<LocalDate> processTheDate(String date) {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");

        try {
            return Optional.of(LocalDate.parse(date, dateTimeFormatter));
        } catch (DateTimeParseException e) {
            if (nextProcessor != null) {
                return nextProcessor.processTheDate(date);
            }
        }

        return Optional.empty();
    }
}
