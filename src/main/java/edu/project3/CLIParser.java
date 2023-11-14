package edu.project3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public final class CLIParser {

    private CLIParser() {
    }

    public static LocalDateTime parseDateFromString(String value) {
        LocalDateTime result = null;

        if (value != null) {
            try {
                result = LocalDateTime.parse(value);
            } catch (DateTimeParseException e) {
                try {
                    result = LocalDate.parse(value).atStartOfDay();
                } catch (DateTimeParseException ignored) {
                }
            }
        }

        return result;
    }
}
