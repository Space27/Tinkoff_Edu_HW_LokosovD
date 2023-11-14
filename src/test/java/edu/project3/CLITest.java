package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

class CLITest {

    @Test
    @DisplayName("Парсинг корректной строки в виде даты")
    void parseDateFromString_shouldConvertStringIntoLocalDate(){
        String string = "2018-05-03";

        LocalDateTime answer = CLIParser.parseDateFromString(string);

        assertThat(answer)
            .isEqualTo(LocalDate.parse(string).atStartOfDay());
    }

    @Test
    @DisplayName("Парсинг корректной строки в виде даты с локальным временем")
    void parseDateFromString_shouldConvertStringIntoLocalDateTime(){
        String string = "2018-05-03T08:20:20";

        LocalDateTime answer = CLIParser.parseDateFromString(string);

        assertThat(answer)
            .isEqualTo(LocalDateTime.parse(string));
    }

    @Test
    @DisplayName("Парсинг некорректной строки")
    void parseDateFromString_shouldReturnNullForNotValidString(){
        String string = "2018-05-03 08:20:20";

        LocalDateTime answer = CLIParser.parseDateFromString(string);

        assertThat(answer)
            .isNull();
    }
}
