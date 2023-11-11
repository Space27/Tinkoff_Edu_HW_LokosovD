package edu.project3;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LogParser {

    private LogParser() {
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static Log parseString(String string) {
        Pattern pattern =
            Pattern.compile("([0-9.]{8,16}) -(.+)?- \\[(.*)] \"(.*)\" (\\d+) (\\d+) \"(.*)\" \"(.*)\"");
        Matcher matcher = pattern.matcher(string);
        final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);

        if (matcher.find()) {
            try {
                InetAddress ip = InetAddress.getByName(matcher.group(1));
                LocalDateTime time = LocalDateTime.parse(matcher.group(3), dateTimeFormatter);
                URI uri = new URI(matcher.group(7));

                int status = Integer.parseInt(matcher.group(5));
                int bodyBytes = Integer.parseInt(matcher.group(6));

                return new Log(ip, matcher.group(2), time, matcher.group(4), status, bodyBytes, uri, matcher.group(8));
            } catch (UnknownHostException | URISyntaxException | DateTimeParseException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static List<Log> parseStringList(List<String> strings) {
        return strings.stream()
            .map(LogParser::parseString)
            .filter(Objects::nonNull)
            .toList();
    }

    public static List<Log> filterLogsForDate(List<Log> logs, LocalDateTime from, LocalDateTime to) {
        return logs.stream()
            .filter(log -> log.timeLocal().isAfter(from) && log.timeLocal().isBefore(to))
            .toList();
    }
}
