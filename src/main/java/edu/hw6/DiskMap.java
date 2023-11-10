package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    private final Path path;

    public DiskMap(Path path) throws IOException {
        this.path = path;
        if (Files.exists(path)) {
            Files.delete(path);
        }
        Files.createFile(path);
    }

    @Override
    public int size() {
        try {
            return Files.readAllLines(path).size();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return keySet().contains((String) key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains((String) value);
    }

    @Override
    public String get(Object key) {
        for (var i : entrySet()) {
            if (i.getKey().equals(key)) {
                return i.getValue();
            }
        }

        return null;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        String prevVal = null;
        if (containsKey(key)) {
            prevVal = get(key);
            remove(key);
        }

        try {
            Files.writeString(path, String.format("%s:%s\n", key, value), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return prevVal;
    }

    @Override
    public String remove(Object key) {
        StringBuilder result = new StringBuilder();
        String prevVal = null;

        try {
            for (String i : Files.readAllLines(path)) {
                if (!i.split(":")[0].equals(key)) {
                    result.append(i).append('\n');
                } else {
                    prevVal = i.split(":")[0];
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.writeString(path, result.toString(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return prevVal;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        for (var i : m.keySet()) {
            put(i, m.get(i));
        }
    }

    @Override
    public void clear() {
        try {
            Files.writeString(path, "", StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                .map(line -> line.split(":")[0])
                .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Collection<String> values() {
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                .map(line -> line.split(":")[1])
                .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        try (Stream<String> lines = Files.lines(path)) {
            return new HashSet<>(lines
                .collect(Collectors.toMap(line -> line.split(":")[0], line -> line.split(":")[1]))
                .entrySet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
