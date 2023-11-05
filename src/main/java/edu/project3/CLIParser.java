package edu.project3;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public final class CLIParser {

    private CLIParser() {
    }

    public static LocalDate parseDateFromString(String string) {
        LocalDate result = null;

        if (string != null) {
            try {
                result = LocalDate.parse(string);
            } catch (DateTimeParseException ignored) {
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
