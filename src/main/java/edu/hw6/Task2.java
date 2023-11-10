package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Task2 {

    private Task2() {
    }

    public static Path cloneFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
            return path;
        }

        String[] splitFileName = path.toString().split("\\.");
        String newFileName = splitFileName[0];
        String extension = splitFileName.length > 1 ? "." + splitFileName[1] : "";

        String fileCopy = String.format("%s - копия%s", newFileName, extension);
        for (int i = 1; Files.exists(Path.of(fileCopy)); ++i) {
            fileCopy = String.format("%s - копия (%d)%s", newFileName, i, extension);
        }

        Files.copy(path, Path.of(fileCopy));
        return Path.of(fileCopy);
    }
}
