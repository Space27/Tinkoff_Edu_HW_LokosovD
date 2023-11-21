package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class TodayProcessor extends FormatProcessor {

    public TodayProcessor(FormatProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public Optional<LocalDate> processTheDate(String date) {
        if (date.equals("today")) {
            return Optional.of(LocalDate.now());
        } else if (nextProcessor != null) {
            return nextProcessor.processTheDate(date);
        }

        return Optional.empty();
    }
}
