package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class FormatProcessor {

    public FormatProcessor nextProcessor;

    public FormatProcessor(FormatProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    public abstract Optional<LocalDate> processTheDate(String date);
}
