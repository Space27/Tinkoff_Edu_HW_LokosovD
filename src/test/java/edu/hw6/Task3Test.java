package edu.hw6;

import edu.hw6.Task3.AbstractFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import static edu.hw6.Task3.AbstractFilter.equal;
import static edu.hw6.Task3.AbstractFilter.globMatches;
import static edu.hw6.Task3.AbstractFilter.largerThan;
import static edu.hw6.Task3.AbstractFilter.lowerThan;
import static edu.hw6.Task3.AbstractFilter.magicNumber;
import static edu.hw6.Task3.AbstractFilter.regexContains;
import static edu.hw6.Task3.AbstractFilter.regexMatches;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {

    public static final AbstractFilter regularFile = Files::isRegularFile;
    public static final AbstractFilter readable = Files::isReadable;
    public static final AbstractFilter writable = Files::isWritable;
    Path dir = Path.of("src/test/java/edu/hw6/FilterFiles");

    @Test
    @DisplayName("Фильтр на проверку записывания")
    void writable_shouldFilterOnlyWritableFiles() {
        DirectoryStream.Filter<Path> filter = writable;
        List<String> expectedFileNames = List.of("big_file.txt", "NotReadable.txt", "txt.png", "img.png", "NotAFile");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            Stream<Path> stream = StreamSupport.stream(entries.spliterator(), false);
            List<String> result = stream.map(Path::getFileName).map(Path::toString).toList();

            assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expectedFileNames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Фильтр на проверку чтения")
    void writable_shouldFilterOnlyReadableFiles() {
        DirectoryStream.Filter<Path> filter = readable;
        List<String> expectedFileNames = List.of("big_file.txt", "txt.png", "img.png", "NotAFile", "NotWritable");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            Stream<Path> stream = StreamSupport.stream(entries.spliterator(), false);
            List<String> result = stream.map(Path::getFileName).map(Path::toString).toList();

            assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expectedFileNames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Фильтр на файл")
    void writable_shouldFilterOnlyRegularFiles() {
        DirectoryStream.Filter<Path> filter = regularFile;
        List<String> expectedFileNames =
            List.of("big_file.txt", "txt.png", "img.png", "NotWritable", "NotReadable.txt");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            Stream<Path> stream = StreamSupport.stream(entries.spliterator(), false);
            List<String> result = stream.map(Path::getFileName).map(Path::toString).toList();

            assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expectedFileNames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Фильтр на конкретный размер")
    void writable_shouldFilterOnlyFilesWithSize() {
        DirectoryStream.Filter<Path> filter = equal(5);
        List<String> expectedFileNames = List.of("NotReadable.txt");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            Stream<Path> stream = StreamSupport.stream(entries.spliterator(), false);
            List<String> result = stream.map(Path::getFileName).map(Path::toString).toList();

            assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expectedFileNames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Фильтр на размер больше")
    void writable_shouldFilterOnlyFilesWithLargerSize() {
        DirectoryStream.Filter<Path> filter = largerThan(5);
        List<String> expectedFileNames = List.of("big_file.txt", "txt.png", "img.png");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            Stream<Path> stream = StreamSupport.stream(entries.spliterator(), false);
            List<String> result = stream.map(Path::getFileName).map(Path::toString).toList();

            assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expectedFileNames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Фильтр на размер меньше")
    void writable_shouldFilterOnlyFilesWithLowerSize() throws IOException {
        DirectoryStream.Filter<Path> filter = lowerThan((int) Files.size(dir.resolve("NotReadable.txt")));
        List<String> expectedFileNames = List.of("NotAFile", "NotWritable");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            Stream<Path> stream = StreamSupport.stream(entries.spliterator(), false);
            List<String> result = stream.map(Path::getFileName).map(Path::toString).toList();

            assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expectedFileNames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Фильтр на регулярное выражение")
    void writable_shouldFilterOnlyFilesWithRegex() {
        DirectoryStream.Filter<Path> filter = regexMatches("Not.*");
        List<String> expectedFileNames = List.of("NotAFile", "NotWritable", "NotReadable.txt");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            Stream<Path> stream = StreamSupport.stream(entries.spliterator(), false);
            List<String> result = stream.map(Path::getFileName).map(Path::toString).toList();

            assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expectedFileNames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Фильтр на содержания регулярного выражения")
    void writable_shouldFilterOnlyFilesWithFindRegex() {
        DirectoryStream.Filter<Path> filter = regexContains("pn");
        List<String> expectedFileNames = List.of("img.png", "txt.png");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            Stream<Path> stream = StreamSupport.stream(entries.spliterator(), false);
            List<String> result = stream.map(Path::getFileName).map(Path::toString).toList();

            assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expectedFileNames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Фильтр на glob")
    void writable_shouldFilterOnlyGlobMatches() {
        DirectoryStream.Filter<Path> filter = globMatches("*.txt");
        List<String> expectedFileNames = List.of("big_file.txt", "NotReadable.txt");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            Stream<Path> stream = StreamSupport.stream(entries.spliterator(), false);
            List<String> result = stream.map(Path::getFileName).map(Path::toString).toList();

            assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expectedFileNames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Фильтр на начальные идентификаторы")
    void writable_shouldFilterOnlyMagicNumberMatched() {
        DirectoryStream.Filter<Path> filter = magicNumber(0x89, 'P', 'N', 'G');
        List<String> expectedFileNames = List.of("txt.png", "img.png");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            Stream<Path> stream = StreamSupport.stream(entries.spliterator(), false);
            List<String> result = stream.map(Path::getFileName).map(Path::toString).toList();

            assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expectedFileNames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Тест and")
    void writable_shouldFilterWithAnd() {
        DirectoryStream.Filter<Path> filter = readable
            .and(globMatches("*.txt"));
        List<String> expectedFileNames = List.of("big_file.txt");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            Stream<Path> stream = StreamSupport.stream(entries.spliterator(), false);
            List<String> result = stream.map(Path::getFileName).map(Path::toString).toList();

            assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expectedFileNames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Тест or")
    void writable_shouldFilterWithOr() {
        DirectoryStream.Filter<Path> filter = largerThan(100_000)
            .or(regexMatches(".*File.*"));
        List<String> expectedFileNames = List.of("NotAFile", "txt.png", "img.png");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            Stream<Path> stream = StreamSupport.stream(entries.spliterator(), false);
            List<String> result = stream.map(Path::getFileName).map(Path::toString).toList();

            assertThat(result)
                .containsExactlyInAnyOrderElementsOf(expectedFileNames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
