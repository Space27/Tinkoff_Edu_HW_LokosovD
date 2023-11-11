package edu.project3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileSource implements LogSource {

    private final Path path;

    public FileSource(Path path) {
        this.path = path;
    }

    @Override
    public List<String> getStringList() {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            return List.of();
        }
    }
}
