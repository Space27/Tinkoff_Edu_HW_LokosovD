package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

class Task6Test {

    @Test
    @DisplayName("Функция должна вернуть свободные порты")
    void scanPortsAndGetThem_shouldReturnStringWithFreePorts() throws IOException {
        String result = Task6.scanPortsAndGetThem();

        assertThat(result.lines())
            .isNotEmpty()
            .hasSizeLessThan(49152 * 2);

    }
}
