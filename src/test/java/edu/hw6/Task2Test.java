package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {

    @Test
    @DisplayName("Создание одной копии")
    void cloneFile_ShouldCopyFile() throws IOException {
        Path path = Path.of("src/test/java/edu/hw6/clone.txt");

        Path newPath = Task2.cloneFile(path);

        assertThat(Files.exists(Path.of("src/test/java/edu/hw6/clone - копия.txt")))
            .isTrue();
        assertThat(Files.readAllBytes(path))
            .isEqualTo(Files.readAllBytes(newPath));

        Files.delete(newPath);
    }

    @Test
    @DisplayName("Создание несуществующего файла")
    void cloneFile_ShouldCreateNotExistingFile() throws IOException {
        Path path = Path.of("src/test/java/edu/hw6/not_existing_file.txt");

        Path newPath = Task2.cloneFile(path);

        assertThat(Files.exists(path))
            .isTrue();
        assertThat(path)
            .isEqualTo(newPath);

        Files.delete(newPath);
    }

    @Test
    @DisplayName("Создание двух копий")
    void cloneFile_ShouldCopyTwoTimes() throws IOException {
        Path path = Path.of("src/test/java/edu/hw6/clone.txt");

        Path newPath1 = Task2.cloneFile(path);
        Path newPath2 = Task2.cloneFile(path);

        assertThat(Files.exists(Path.of("src/test/java/edu/hw6/clone - копия.txt")))
            .isTrue();
        assertThat(Files.exists(Path.of("src/test/java/edu/hw6/clone - копия (1).txt")))
            .isTrue();
        assertThat(Files.readAllBytes(path))
            .isEqualTo(Files.readAllBytes(newPath1));
        assertThat(Files.readAllBytes(path))
            .isEqualTo(Files.readAllBytes(newPath2));

        Files.delete(newPath1);
        Files.delete(newPath2);
    }

    @Test
    @DisplayName("Создание трех копий")
    void cloneFile_ShouldCopyThreeTimes() throws IOException {
        Path path = Path.of("src/test/java/edu/hw6/clone.txt");

        Path newPath1 = Task2.cloneFile(path);
        Path newPath2 = Task2.cloneFile(path);
        Path newPath3 = Task2.cloneFile(path);

        assertThat(Files.exists(Path.of("src/test/java/edu/hw6/clone - копия (2).txt")))
            .isTrue();
        assertThat(Files.readAllBytes(path))
            .isEqualTo(Files.readAllBytes(newPath3));

        Files.delete(newPath1);
        Files.delete(newPath2);
        Files.delete(newPath3);
    }

    @Test
    @DisplayName("Создание копии копии")
    void cloneFile_ShouldCopyCopy() throws IOException {
        Path path = Path.of("src/test/java/edu/hw6/clone.txt");

        Path newPath1 = Task2.cloneFile(path);
        Path newPath2 = Task2.cloneFile(newPath1);

        assertThat(Files.exists(Path.of("src/test/java/edu/hw6/clone - копия - копия.txt")))
            .isTrue();
        assertThat(Files.readAllBytes(path))
            .isEqualTo(Files.readAllBytes(newPath2));

        Files.delete(newPath1);
        Files.delete(newPath2);
    }
}
