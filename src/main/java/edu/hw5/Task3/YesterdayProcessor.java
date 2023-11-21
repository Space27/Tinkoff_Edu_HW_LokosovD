package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class YesterdayProcessor extends FormatProcessor {

    public YesterdayProcessor(FormatProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public Optional<LocalDate> processTheDate(String date) {
        if (date.equals("yesterday") || date.equals("1 day ago")) {
            return Optional.of(LocalDate.now().minusDays(1));
        } else if (nextProcessor != null) {
            return nextProcessor.processTheDate(date);
        }

        return Optional.empty();
    }
}
