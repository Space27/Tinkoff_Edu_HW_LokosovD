package edu.hw9;

import edu.hw9.Task2.Task2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {

    @Test
    @DisplayName("Несуществующий корень")
    void findDirectoriesWithMoreThan1000Files_shouldReturnEmptyListForNotExistingRoot() {
        Path path = Path.of("notexists");

        List<Path> result = Task2.findDirectoriesWithMoreThan1000Files(path);

        assertThat(result)
            .isEmpty();
    }

    @Test
    @DisplayName("Пустая директория")
    void findDirectoriesWithMoreThan1000Files_shouldReturnEmptyListForEmptyDir() throws IOException {
        Path path = Path.of("empty");
        Files.createDirectory(path);

        List<Path> result = Task2.findDirectoriesWithMoreThan1000Files(path);

        Files.delete(path);

        assertThat(result)
            .isEmpty();
    }

    @Test
    @DisplayName("Одна директория с 1000 файлов")
    void findDirectoriesWithMoreThan1000Files_shouldReturnDirNameWith1000Files() throws IOException {
        Path path = Path.of("1000files");
        Files.createDirectory(path);
        for (int i = 0; i < 1000; ++i) {
            Files.createFile(path.resolve(String.valueOf(i)));
        }

        List<Path> result = Task2.findDirectoriesWithMoreThan1000Files(path);

        try (Stream<Path> stream = Files.walk(path)) {
            stream.sorted(Comparator.reverseOrder())
                .forEach(file -> {
                    try {
                        Files.delete(file);
                    } catch (IOException ignored) {
                    }
                });
        }

        assertThat(result)
            .hasSize(1)
            .contains(path);
    }

    @Test
    @DisplayName("Одна директория с 999 файлов")
    void findDirectoriesWithMoreThan1000Files_shouldReturnEmptyListFor999Files() throws IOException {
        Path path = Path.of("999files");
        Files.createDirectory(path);
        for (int i = 0; i < 999; ++i) {
            Files.createFile(path.resolve(String.valueOf(i)));
        }

        List<Path> result = Task2.findDirectoriesWithMoreThan1000Files(path);

        try (Stream<Path> stream = Files.walk(path)) {
            stream.sorted(Comparator.reverseOrder())
                .forEach(file -> {
                    try {
                        Files.delete(file);
                    } catch (IOException ignored) {
                    }
                });
        }

        assertThat(result)
            .isEmpty();
    }

    @Test
    @DisplayName("Одна директория с двумя директориями по 500 файлов")
    void findDirectoriesWithMoreThan1000Files_shouldReturnDirNameWithTwoDirsWith500() throws IOException {
        Path path = Path.of("2dirs");
        Files.createDirectory(path);
        Files.createDirectory(path.resolve("1dir"));
        Files.createDirectory(path.resolve("2dir"));
        for (int i = 0; i < 500; ++i) {
            Files.createFile(path.resolve("1dir").resolve(String.valueOf(i)));
        }
        for (int i = 0; i < 500; ++i) {
            Files.createFile(path.resolve("2dir").resolve(String.valueOf(i)));
        }

        List<Path> result = Task2.findDirectoriesWithMoreThan1000Files(path);

        try (Stream<Path> stream = Files.walk(path)) {
            stream.sorted(Comparator.reverseOrder())
                .forEach(file -> {
                    try {
                        Files.delete(file);
                    } catch (IOException ignored) {
                    }
                });
        }

        assertThat(result)
            .hasSize(1)
            .contains(path);
    }

    @Test
    @DisplayName("Директория с директорией с 1001 файлом и директорией с 999")
    void findDirectoriesWithMoreThan1000Files_shouldReturnAllDirNamesWith1000Files() throws IOException {
        Path path = Path.of("dir");
        Files.createDirectory(path);
        Files.createDirectory(path.resolve("1dir"));
        Files.createDirectory(path.resolve("2dir"));
        List<Path> expected = List.of(path, path.resolve("1dir"));
        for (int i = 0; i < 999; ++i) {
            Files.createFile(path.resolve("1dir").resolve(String.valueOf(i)));
            Files.createFile(path.resolve("2dir").resolve(String.valueOf(i)));
        }
        Files.createFile(path.resolve("1dir").resolve(String.valueOf(999)));
        Files.createFile(path.resolve("1dir").resolve(String.valueOf(1000)));

        List<Path> result = Task2.findDirectoriesWithMoreThan1000Files(path);

        try (Stream<Path> stream = Files.walk(path)) {
            stream.sorted(Comparator.reverseOrder())
                .forEach(file -> {
                    try {
                        Files.delete(file);
                    } catch (IOException ignored) {
                    }
                });
        }

        assertThat(result)
            .containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    @DisplayName("Директория с 2 директориями в которых 1000 файлов")
    void findDirectoriesWithMoreThan1000Files_shouldReturnDirNameWith1000DirsWithOneFile() throws IOException {
        Path path = Path.of("dir");
        Files.createDirectory(path);
        for (int i = 0; i < 1000; ++i) {
            Files.createDirectory(path.resolve(String.valueOf(i)));
            Files.createFile(path.resolve(String.valueOf(i)).resolve(String.valueOf(i)));
        }

        List<Path> result = Task2.findDirectoriesWithMoreThan1000Files(path);

        try (Stream<Path> stream = Files.walk(path)) {
            stream.sorted(Comparator.reverseOrder())
                .forEach(file -> {
                    try {
                        Files.delete(file);
                    } catch (IOException ignored) {
                    }
                });
        }

        assertThat(result)
            .hasSize(1)
            .contains(path);
    }

    @Test
    @DisplayName("Одна директория с двумя директориями с 500 и 499 файлами")
    void findDirectoriesWithMoreThan1000Files_shouldReturnEmptyListForTwoDirsWith500And499() throws IOException {
        Path path = Path.of("2dirs");
        Files.createDirectory(path);
        Files.createDirectory(path.resolve("1dir"));
        Files.createDirectory(path.resolve("2dir"));
        for (int i = 0; i < 500; ++i) {
            Files.createFile(path.resolve("1dir").resolve(String.valueOf(i)));
        }
        for (int i = 0; i < 499; ++i) {
            Files.createFile(path.resolve("2dir").resolve(String.valueOf(i)));
        }

        List<Path> result = Task2.findDirectoriesWithMoreThan1000Files(path);

        try (Stream<Path> stream = Files.walk(path)) {
            stream.sorted(Comparator.reverseOrder())
                .forEach(file -> {
                    try {
                        Files.delete(file);
                    } catch (IOException ignored) {
                    }
                });
        }

        assertThat(result)
            .isEmpty();
    }

    @Test
    @DisplayName("Несуществующий корень")
    void findFilesForPredicate_shouldReturnEmptyListForNotExistingRoot() {
        Path path = Path.of("notexists");

        List<Path> result = Task2.findFilesForPredicate(path, Files::isRegularFile);

        assertThat(result)
            .isEmpty();
    }

    @Test
    @DisplayName("Пустая директория")
    void findFilesForPredicate_shouldReturnEmptyListForEmptyDir() throws IOException {
        Path path = Path.of("empty");
        Files.createDirectory(path);

        List<Path> result = Task2.findFilesForPredicate(path, Files::isRegularFile);

        Files.delete(path);

        assertThat(result)
            .isEmpty();
    }

    @Test
    @DisplayName("Одна директория с 10ю файлами")
    void findFilesForPredicate_shouldReturnAllRegularFiles() throws IOException {
        Path path = Path.of("10files");
        Files.createDirectory(path);
        for (int i = 0; i < 10; ++i) {
            Files.createFile(path.resolve(String.valueOf(i)));
        }

        List<Path> result = Task2.findFilesForPredicate(path, Files::isRegularFile);

        try (Stream<Path> stream = Files.walk(path)) {
            stream.sorted(Comparator.reverseOrder())
                .forEach(file -> {
                    try {
                        Files.delete(file);
                    } catch (IOException ignored) {
                    }
                });
        }

        assertThat(result)
            .hasSize(10);
    }

    @Test
    @DisplayName("Директория с 3 директориями по 1000 файлов")
    void findFilesForPredicate_shouldReturnAllRegularFilesInDirectories() throws IOException {
        Path path = Path.of("dir");
        Files.createDirectory(path);
        Files.createDirectory(path.resolve("1dir"));
        Files.createDirectory(path.resolve("2dir"));
        Files.createDirectory(path.resolve("3dir"));
        for (int i = 0; i < 1000; ++i) {
            Files.createFile(path.resolve("1dir").resolve(String.valueOf(i)));
            Files.createFile(path.resolve("2dir").resolve(String.valueOf(i)));
            Files.createFile(path.resolve("3dir").resolve(String.valueOf(i)));
        }

        List<Path> result = Task2.findFilesForPredicate(path, (file) -> file.getFileName().toString().equals("0"));

        try (Stream<Path> stream = Files.walk(path)) {
            stream.sorted(Comparator.reverseOrder())
                .forEach(file -> {
                    try {
                        Files.delete(file);
                    } catch (IOException ignored) {
                    }
                });
        }

        assertThat(result)
            .hasSize(3);
    }
}
