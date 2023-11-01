package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class DaysAgoProcessor extends FormatProcessor {

    public DaysAgoProcessor(FormatProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public Optional<LocalDate> processTheDate(String date) {
        if (date.matches("\\d+ days ago") && Integer.parseInt(date.split(" ")[0]) > 1) {
            return Optional.of(LocalDate.now().minusDays(Integer.parseInt(date.split(" ")[0])));
        } else if (nextProcessor != null) {
            return nextProcessor.processTheDate(date);
        }

        return Optional.empty();
    }
}
