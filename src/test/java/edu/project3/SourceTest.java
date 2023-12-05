package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class SourceTest {

    @Test
    @DisplayName("Взятие строк из файла")
    void fileSource_shouldReadAllLogsInFile() {
        Path path = Path.of("src/test/java/edu/project3/logs.log");

        List<String> result = new FileSource(path).readStringsFromSource();

        assertThat(result)
            .hasSize(442)
            .contains( //первая строка файла
                "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"")
            .contains( //последняя строка файла
                "91.239.186.133 - - [17/May/2015:11:05:17 +0000] \"GET /downloads/product_2 HTTP/1.1\" 404 339 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.9)\"");
    }

    @Test
    @DisplayName("Взятие строк из несуществующего файла")
    void fileSource_shouldReturnEmptyListForNotExistingFile() {
        Path path = Path.of("src/test/java/edu/project3/logs.lo");

        List<String> result = new FileSource(path).readStringsFromSource();

        assertThat(result)
            .isEmpty();
    }

    @Test
    @DisplayName("Взятие строк с сайта")
    void httpSource_shouldReadAllLogsInWeb() throws URISyntaxException {
        URI path = new URI("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");

        List<String> result = new HttpSource(path).readStringsFromSource();

        assertThat(result)
            .isNotEmpty()
            .contains( //первая строка сайта
                "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"")
            .contains( //последняя строка файла
                "79.136.114.202 - - [04/Jun/2015:07:06:35 +0000] \"GET /downloads/product_1 HTTP/1.1\" 404 334 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\"");
    }

    @Test
    @DisplayName("Взятие строк с несуществующего сайта")
    void httpSource_shouldReturnEmptyListForNotExistingWeb() throws URISyntaxException {
        URI path = new URI("https://raw.githubusercontnt.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");

        List<String> result = new HttpSource(path).readStringsFromSource();

        assertThat(result)
            .isEmpty();
    }
}
