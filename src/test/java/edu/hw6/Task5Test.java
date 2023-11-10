package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import static org.assertj.core.api.Assertions.assertThat;

class Task5Test {

    @Test
    @DisplayName("Тест вывода long")
    void hackerNewsTopStories_shouldReturnJSONAsLongArray()
        throws URISyntaxException, IOException, InterruptedException {
        final long firstId = 38222596;

        long[] result = Task5.hackerNewsTopStories();

        assertThat(result)
            .hasSizeGreaterThan(200)
            .contains(firstId);
    }

    @Test
    @DisplayName("Тест вывода названия статьи")
    void news_shouldReturnNewsNameForID()
        throws URISyntaxException, IOException, InterruptedException {
        final String title = "JDK 21 Release Notes";

        String result = Task5.news(37570037);

        assertThat(result)
            .isEqualTo(title);
    }
}
