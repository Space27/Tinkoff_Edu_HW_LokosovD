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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LogParser {

    private LogParser() {
    }

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Pattern NGINX_LOG_PATTERN =
        Pattern.compile("([0-9.]{8,16}) -(.+)?- \\[(.*)] \"(.*)\" (\\d+) (\\d+) \"(.*)\" \"(.*)\"");
    private static final DateTimeFormatter NGINX_DATE_FORMAT =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);

    private enum NGINXStructure {
        IP(1),
        USER(2),
        DATE(3),
        REQUEST(4),
        STATUS(5),
        BYTES(6),
        URI(7),
        HTTP_AGENT(8);

        private final int groupNum;

        NGINXStructure(int groupNum) {
            this.groupNum = groupNum;
        }

        int getGroup() {
            return groupNum;
        }
    }

    public static Log getLogFromString(String string) {
        Matcher matcher = NGINX_LOG_PATTERN.matcher(string);

        if (matcher.find()) {
            try {
                InetAddress ip = InetAddress.getByName(matcher.group(NGINXStructure.IP.getGroup()));
                LocalDateTime time =
                    LocalDateTime.parse(matcher.group(NGINXStructure.DATE.getGroup()), NGINX_DATE_FORMAT);
                URI uri = new URI(matcher.group(NGINXStructure.URI.getGroup()));

                int status = Integer.parseInt(matcher.group(NGINXStructure.STATUS.getGroup()));
                int bodyBytes = Integer.parseInt(matcher.group(NGINXStructure.BYTES.getGroup()));

                String user = matcher.group(NGINXStructure.USER.getGroup());
                String request = matcher.group(NGINXStructure.REQUEST.getGroup());
                String httpAgent = matcher.group(NGINXStructure.HTTP_AGENT.getGroup());

                return new Log(ip, user, time, request, status, bodyBytes, uri, httpAgent);
            } catch (UnknownHostException | URISyntaxException | DateTimeParseException e) {
                LOGGER.error(e);
                return null;
            }
        } else {
            return null;
        }
    }

    public static List<Log> getLogsFromStrings(List<String> strings) {
        return strings.stream()
            .map(LogParser::getLogFromString)
            .filter(Objects::nonNull)
            .toList();
    }

    public static List<Log> filterLogsForDate(List<Log> logs, LocalDateTime from, LocalDateTime to) {
        return logs.stream()
            .filter(log -> log.timeLocal().isAfter(from) && log.timeLocal().isBefore(to))
            .toList();
    }
}
