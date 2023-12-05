package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class LogStatTest {

    private final List<Log> logs =
        LogParser.getLogsFromStrings(new FileSource(Path.of("src/test/java/edu/project3/stat.log")).readStringsFromSource());
    private final LogStat stat = new LogStat(logs);

    @Test
    @DisplayName("Число запросов")
    void getRequestCount_shouldReturnLogCount() {
        int result = (int) stat.getRequestCount();

        assertThat(result)
            .isEqualTo(10);
    }

    @Test
    @DisplayName("Средний размер ответа")
    void getAvgServerAnswer_shouldReturnAvgRequestSize() {
        int result = stat.getAvgServerAnswer();

        assertThat(result)
            .isEqualTo(164);
    }

    @Test
    @DisplayName("Самые часто запрашиваемые ресурсы")
    void getMostRequestedResources_shouldReturnMostRequestedResources() {
        Map<String, Long> result = stat.getMostRequestedResources();

        Map<String, Long> expected = new LinkedHashMap<>(){{
            put("product_1", 8L);
            put("product_2", 2L);
        }};

        assertThat(result)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Самые часто возвращаемые коды")
    void getMostReturnedStatuses_shouldReturnMostReturnedStatuses() {
        Map<Integer, Long> result = stat.getMostReturnedStatuses();

        Map<Integer, Long> expected = new LinkedHashMap<>(){{
            put(304, 6L);
            put(404, 2L);
            put(200, 2L);
        }};

        assertThat(result)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Самые часто запрашиваемые ресурсы с ошибкой 404")
    void getMostRequestedResourcesWithServerError_shouldReturnMostRequestedResourcesWithServerError() {
        Map<String, Long> result = stat.getMostRequestedResourcesWithServerError();

        Map<String, Long> expected = new LinkedHashMap<>(){{
            put("product_1", 1L);
            put("product_2", 1L);
        }};

        assertThat(result)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Самые частые агенты")
    void getMostFrequentAgents_shouldReturnMostFrequentAgents() {
        Map<String, Long> result = stat.getMostFrequentAgents();

        Map<String, Long> expected = new LinkedHashMap<>(){{
            put("0.8.10.3", 4L);
            put("0.8.16~exp12ubuntu10.21", 4L);
            put("0.8.16~exp12ubuntu10.17", 2L);
        }};

        assertThat(result)
            .isEqualTo(expected);
    }
}
