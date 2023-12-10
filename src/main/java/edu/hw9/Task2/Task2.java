package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public final class Task2 {

    private Task2() {
    }

    private final static ForkJoinPool THREADS = new ForkJoinPool();

    public static List<Path> findDirectoriesWithMoreThan1000Files(Path root) {
        CountFiles task = new CountFiles(root);
        return THREADS.invoke(task).bigDirectories();
    }

    public static List<Path> findFilesForPredicate(Path root, DirectoryStream.Filter<Path> filter) {
        List<Path> result = new ArrayList<>();
        List<RecursiveTask<List<Path>>> tasks = new ArrayList<>();

        if (Files.isRegularFile(root)) {
            return List.of();
        }
        try {
            addTasksAndFindForPredicate(result, tasks, root, filter);
        } catch (IOException e) {
            return List.of();
        }

        result.addAll(ForkJoinTask.invokeAll(tasks).stream()
            .map(ForkJoinTask::join)
            .flatMap(Collection::stream)
            .toList());

        return result;
    }

    private static void addTasksAndFindForPredicate(
        List<Path> result,
        List<RecursiveTask<List<Path>>> tasks,
        Path root,
        DirectoryStream.Filter<Path> filter
    )
        throws IOException {
        try (DirectoryStream<Path> files = Files.newDirectoryStream(root)) {
            for (Path path : files) {
                if (Files.isRegularFile(path) && filter.accept(path)) {
                    result.add(path);
                } else {
                    tasks.add(new RecursiveTask<>() {
                        @Override
                        protected List<Path> compute() {
                            return findFilesForPredicate(path, filter);
                        }
                    });
                }
            }
        }
    }

}
