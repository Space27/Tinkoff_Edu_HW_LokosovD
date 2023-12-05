package edu.project3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CLIParser {

    private CLIParser() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static LocalDateTime parseDateFromString(String value) {
        LocalDateTime result = null;

        if (value != null) {
            try {
                result = LocalDateTime.parse(value);
            } catch (DateTimeParseException localDateTimeE) {
                LOGGER.error(localDateTimeE);
                try {
                    result = LocalDate.parse(value).atStartOfDay();
                } catch (DateTimeParseException localDateE) {
                    LOGGER.error(localDateE);
                }
            }
        }

        return result;
    }
}
