package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public abstract class FormatProcessor {

    public FormatProcessor nextProcessor;

    public FormatProcessor(FormatProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    public void setNext(FormatProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    public void setNextByList(List<FormatProcessor> nextProcessors) {
        this.nextProcessor = nextProcessors.get(0);

        if (nextProcessors.size() > 1) {
            nextProcessors.get(0).setNextByList(nextProcessors.subList(1, nextProcessors.size()));
        }
    }

    public abstract Optional<LocalDate> processTheDate(String date);
}
