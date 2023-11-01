package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class TomorrowProcessor extends FormatProcessor {

    public TomorrowProcessor(FormatProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public Optional<LocalDate> processTheDate(String date) {
        if (date.equals("tomorrow")) {
            return Optional.of(LocalDate.now().plusDays(1));
        } else if (nextProcessor != null) {
            return nextProcessor.processTheDate(date);
        }

        return Optional.empty();
    }
}
