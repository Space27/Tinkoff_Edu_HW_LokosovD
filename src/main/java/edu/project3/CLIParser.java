package edu.project3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public final class CLIParser {

    private CLIParser() {
    }

    public static LocalDateTime parseDateFromString(String string) {
        LocalDateTime result = null;

        if (string != null) {
            try {
                result = LocalDateTime.parse(string);
            } catch (DateTimeParseException e) {
                try {
                    result = LocalDate.parse(string).atStartOfDay();
                } catch (DateTimeParseException ignored) {
                }
            }
        }

        return result;
    }

    public static LogAnalyzer.Format parseOutputFormatFromString(String string) {
        LogAnalyzer.Format result = LogAnalyzer.Format.markdown;

        if (string != null && string.equals("adoc")) {
            result = LogAnalyzer.Format.adoc;
        }

        return result;
    }
}
