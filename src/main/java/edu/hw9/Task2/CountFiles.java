package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CountFiles extends RecursiveTask<CountFiles.DirStat> {

    private final Path directory;
    private final List<CountFiles> tasks;
    private static final int MIN_FILE_COUNT = 1000;

    public CountFiles(Path directory) {
        this.directory = directory;
        this.tasks = new ArrayList<>();
    }

    @Override
    protected CountFiles.DirStat compute() {
        int filesCount = 0;

        if (Files.isRegularFile(directory)) {
            return new DirStat(List.of(), filesCount);
        }

        try {
            filesCount += countRegularFilesAndAddTasks();
        } catch (IOException e) {
            return new DirStat(List.of(), filesCount);
        }

        DirStat result = completeTasksAndCountFiles(filesCount);

        return new DirStat(result.bigDirectories, result.filesCount);
    }

    private int countRegularFilesAndAddTasks() throws IOException {
        int filesCount = 0;

        try (DirectoryStream<Path> files = Files.newDirectoryStream(directory)) {
            for (Path path : files) {
                if (Files.isRegularFile(path)) {
                    ++filesCount;
                } else {
                    tasks.add(new CountFiles(path));
                }
            }
        }

        return filesCount;
    }

    private DirStat completeTasksAndCountFiles(int currentFilesCount) {
        int filesCount = currentFilesCount;
        List<Path> result = new ArrayList<>();

        List<DirStat> completedTasks = ForkJoinTask.invokeAll(tasks).stream()
            .map(ForkJoinTask::join)
            .toList();

        for (DirStat stat : completedTasks) {
            filesCount += stat.filesCount;
            result.addAll(stat.bigDirectories);
        }

        if (filesCount >= MIN_FILE_COUNT) {
            result.add(directory);
        }

        return new DirStat(result, filesCount);
    }

    public record DirStat(List<Path> bigDirectories, int filesCount) {
    }
}
