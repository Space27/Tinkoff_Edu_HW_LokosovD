package edu.hw6.Task3;

import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.Arrays;

@FunctionalInterface
public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    default AbstractFilter and(AbstractFilter readable) {
        return path -> accept(path) && readable.accept(path);
    }

    default AbstractFilter or(AbstractFilter readable) {
        return path -> accept(path) || readable.accept(path);
    }

    static AbstractFilter regexContains(String regex) {
        return path -> path.getFileName().toString().matches(".*" + regex + ".*");
    }

    static AbstractFilter regexMatches(String regex) {
        return path -> path.getFileName().toString().matches(regex);
    }

    static AbstractFilter largerThan(int size) {
        return path -> Files.size(path) > size;
    }

    static AbstractFilter lowerThan(int size) {
        return path -> Files.size(path) < size;
    }

    static AbstractFilter equal(int size) {
        return path -> Files.size(path) == size;
    }

    static AbstractFilter globMatches(String glob) {
        final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
        return path -> pathMatcher.matches(path.getFileName());
    }

    static AbstractFilter magicNumber(int... magicNumbers) {
        byte[] ids = new byte[magicNumbers.length];
        for (int i = 0; i < magicNumbers.length; ++i) {
            ids[i] = (byte) magicNumbers[i];
        }

        return path -> Files.isRegularFile(path) && Files.isReadable(path)
            && Arrays.equals(Arrays.copyOfRange(Files.readAllBytes(path), 0, ids.length), ids);
    }
}
