package edu.project3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileSource implements LogSource {

    private final Path path;
    private static final Logger LOGGER = LogManager.getLogger();

    public FileSource(Path path) {
        this.path = path;
    }

    @Override
    public List<String> readStringsFromSource() {
        List<String> result = new ArrayList<>();

        try {
            result.addAll(Files.readAllLines(path));
        } catch (IOException e) {
            LOGGER.error(e);
        }

        return result;
    }
}
