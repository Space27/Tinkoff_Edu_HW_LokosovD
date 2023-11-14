package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class StatPrinterTest {

    @Test
    @DisplayName("Тест обычного вывода map в adoc")
    void printSortedMapFirstKElements_shouldPrintSortedMapFirstKElementsInAdoc() {
        StatPrinter statPrinter = new StatPrinter(LogAnalyzer.Format.adoc);
        Map<String, String> map = new LinkedHashMap<>() {{
            put("1", "2");
            put("3", "4");
        }};

        String result = statPrinter.printSortedMapFirstKElements(map, 1, "5", "6", "7");
        String expected = ".5\n|===\n|6|7\n\n|1|2\n|===\n";

        assertThat(result)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест обычного вывода map в markdown")
    void printSortedMapFirstKElements_shouldPrintSortedMapFirstKElementsInMarkdown() {
        StatPrinter statPrinter = new StatPrinter(LogAnalyzer.Format.markdown);
        Map<String, String> map = new LinkedHashMap<>() {{
            put("1", "2");
            put("3", "4");
        }};

        String result = statPrinter.printSortedMapFirstKElements(map, 2, "5", "6", "7");
        String expected = "### 5\n|6|7|\n|:-:|-:|\n|1|2|\n|3|4|\n";

        assertThat(result)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест вывода кодов с описанием в adoc")
    void printServerCodes_shouldPrintServerCodesInAdoc() {
        StatPrinter statPrinter = new StatPrinter(LogAnalyzer.Format.adoc);
        Map<Integer, String> map = new LinkedHashMap<>() {{
            put(404, "2");
            put(200, "4");
        }};

        String result = statPrinter.printServerCodes(map, 2, "5", "6", "7", "8");
        String expected = ".5\n|===\n|6|7|8\n\n|404|Not Found|2\n|200|OK|4\n|===\n";

        assertThat(result)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест вывода кодов с описанием в markdown")
    void printServerCodes_shouldPrintServerCodesInMarkdown() {
        StatPrinter statPrinter = new StatPrinter(LogAnalyzer.Format.markdown);
        Map<Integer, String> map = new LinkedHashMap<>() {{
            put(404, "2");
            put(200, "4");
        }};

        String result = statPrinter.printServerCodes(map, 1, "5", "6", "7", "8");
        String expected = "### 5\n|6|7|8|\n|:-:|:-:|-:|\n|404|Not Found|2|\n";

        assertThat(result)
            .isEqualTo(expected);
    }
}
