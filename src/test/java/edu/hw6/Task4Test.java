package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {

    @Test
    @DisplayName("Создание файла с заранее известной строкой")
    void createFileWithStreams_shouldCreateNewFileWithString() throws IOException {
        Path path = Path.of("src/test/java/edu/hw6/C_test.txt");

        Task4.createFileWithStreams(path);

        assertThat(Files.exists(path))
            .isTrue();
        assertThat(Files.readString(path))
            .isEqualTo("Programming is learned by writing programs. ― Brian Kernighan");

        Files.delete(path);
    }

    @Test
    @DisplayName("Создание уже существующего файла")
    void createFileWithStreams_shouldWriteStringInExistingFile() throws IOException {
        Path path = Path.of("src/test/java/edu/hw6/test.txt");

        Task4.createFileWithStreams(path);

        assertThat(Files.exists(path))
            .isTrue();
        assertThat(Files.readString(path))
            .isEqualTo("Programming is learned by writing programs. ― Brian Kernighan");
    }
}
