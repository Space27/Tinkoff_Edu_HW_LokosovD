package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class LogParserTest {

    @Test
    @DisplayName("Парсинг одной строки")
    void parseString_shouldParseValidString() throws UnknownHostException {
        String log =
            "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";

        Log result = LogParser.getLogFromString(log);
        Log expected = new Log(
            InetAddress.getByName("93.180.71.3"),
            " ",
            LocalDateTime.of(2015, 5, 17, 8, 5, 32),
            "GET /downloads/product_1 HTTP/1.1",
            304,
            0,
            URI.create("-"),
            "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
        );

        assertThat(result)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "ab.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
        "[17/May/2015:08:05:32 +0000] \\\"GET /downloads/product_1 HTTP/1.1\\\" 304 0 \\\"-\\\" \\\"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
        "ab.180.71.3 - - [17/Ma/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""})
    @DisplayName("Парсинг невалидной строки")
    void parseString_shouldReturnNullForNotValidString(String log) {
        Log result = LogParser.getLogFromString(log);

        assertThat(result)
            .isNull();
    }

    @Test
    @DisplayName("Парсинг двух строк")
    void parseString_shouldParseTwoValidString() throws UnknownHostException {
        List<String> log = List.of(
            "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
            "217.168.17.5 - - [17/May/2015:08:05:12 +0000] \"GET /downloads/product_2 HTTP/1.1\" 200 3316 \"-\" \"-\""
        );
        List<Log> result = LogParser.getLogsFromStrings(log);
        Log expected1 = new Log(
            InetAddress.getByName("93.180.71.3"),
            " ",
            LocalDateTime.of(2015, 5, 17, 8, 5, 32),
            "GET /downloads/product_1 HTTP/1.1",
            304,
            0,
            URI.create("-"),
            "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
        );
        Log expected2 = new Log(
            InetAddress.getByName("217.168.17.5"),
            " ",
            LocalDateTime.of(2015, 5, 17, 8, 5, 12),
            "GET /downloads/product_2 HTTP/1.1",
            200,
            3316,
            URI.create("-"),
            "-"
        );
        List<Log> expected = List.of(expected1, expected2);

        assertThat(result)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Отсеивание логов по дате")
    void parseString_shouldFilterLogs() throws UnknownHostException {
        List<String> log = List.of(
            "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
            "217.168.17.5 - - [17/May/2015:08:05:12 +0000] \"GET /downloads/product_2 HTTP/1.1\" 200 3316 \"-\" \"-\"",
            "217.168.17.5 - - [17/May/2015:08:05:25 +0000] \"GET /downloads/product_1 HTTP/1.1\" 200 3301 \"-\" \"-\""
        );
        List<Log> result = LogParser.filterLogsForDate(LogParser.getLogsFromStrings(log),
            LocalDateTime.of(2015, 5, 17, 8, 5, 12),
            LocalDateTime.of(2015, 5, 17, 8, 5, 32)
        );
        Log expected = new Log(
            InetAddress.getByName("217.168.17.5"),
            " ",
            LocalDateTime.of(2015, 5, 17, 8, 5, 25),
            "GET /downloads/product_1 HTTP/1.1",
            200,
            3301,
            URI.create("-"),
            "-"
        );

        assertThat(result)
            .hasSize(1)
            .contains(expected);
    }
}
