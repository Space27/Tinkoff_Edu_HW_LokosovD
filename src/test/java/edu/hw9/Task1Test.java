package edu.hw9;

import edu.hw9.Task1.StatsCollector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {

    @Test
    @DisplayName("Одна статистика")
    void stats_shouldReturnCorrectStatsForOneArray() {
        String name = "Test";
        double[] array = new double[] {0.1, 0.2, 1.4, 5.1, 0.3};
        List<StatsCollector.StatResult> expected = List.of(
            new StatsCollector.StatResult(name, 7.1, 7.1 / array.length, 0.1, 5.1)
        );
        StatsCollector statsCollector = new StatsCollector();

        statsCollector.push(name, array);

        List<StatsCollector.StatResult> result = statsCollector.stats();
        statsCollector.close();

        assertThat(result)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Запрос статистики без push")
    void stats_shouldReturnEmptyListIfNoPushes() {
        StatsCollector statsCollector = new StatsCollector();

        List<StatsCollector.StatResult> result = statsCollector.stats();
        statsCollector.close();

        assertThat(result)
            .isEmpty();
    }

    @Test
    @DisplayName("Добавление задачи после запроса статистики")
    void stats_shouldReturnCorrectStatsAfterAnotherStats() {
        String name1 = "Test1";
        String name2 = "Test2";
        double[] array1 = new double[] {0.1, 0.2, 1.4, 5.1, 0.3};
        double[] array2 = new double[] {0.1, 0.2, 1.4, 5., 0.3};
        List<StatsCollector.StatResult> expected = List.of(
            new StatsCollector.StatResult(name1, 7.1, 7.1 / array1.length, 0.1, 5.1),
            new StatsCollector.StatResult(name2, 7., 7. / array2.length, 0.1, 5.)
        );
        StatsCollector statsCollector = new StatsCollector();

        statsCollector.push(name1, array1);
        statsCollector.stats();
        statsCollector.push(name2, array2);

        List<StatsCollector.StatResult> result = statsCollector.stats();
        statsCollector.close();

        assertThat(result)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 10, 20, 50})
    @DisplayName("Задачи добавляют несколько потоков")
    void push_shouldWorkWithSeveralThreads(int n) throws InterruptedException {
        StatsCollector statsCollector = new StatsCollector();
        String name = "Test";
        double[] array = new double[] {0.1, 0.2, 1.4, 5.1, 0.3};
        Callable<Void> task = () -> {
            statsCollector.push(name, array);
            return null;
        };

        ExecutorService executorService = Executors.newFixedThreadPool(n / 2);
        var tasks = Stream.generate(() -> task).limit(n).toList();
        executorService.invokeAll(tasks);

        List<StatsCollector.StatResult> result = statsCollector.stats();

        executorService.shutdown();
        statsCollector.close();

        assertThat(result)
            .hasSize(n)
            .containsOnly(new StatsCollector.StatResult(name, 7.1, 7.1 / array.length, 0.1, 5.1));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 10, 20, 50})
    @DisplayName("Задачи добавляют и заправшивают несколько потоков")
    void pushAndStats_shouldWorkWithSeveralThreads(int n) throws InterruptedException {
        StatsCollector statsCollector = new StatsCollector();
        String name = "Test";
        double[] array = new double[] {0.1, 0.2, 1.4, 5.1, 0.3};
        var expected = new StatsCollector.StatResult(name, 7.1, 7.1 / array.length, 0.1, 5.1);
        Callable<Void> push = () -> {
            statsCollector.push(name, array);
            return null;
        };
        Callable<Void> get = () -> {
            assertThat(statsCollector.stats())
                .hasSizeLessThanOrEqualTo(n)
                .containsOnly(expected);
            return null;
        };

        ExecutorService pushService = Executors.newFixedThreadPool(6);
        ExecutorService getService = Executors.newFixedThreadPool(6);
        var pushTasks = Stream.generate(() -> push).limit(n).toList();
        var getTasks = Stream.generate(() -> get).limit(n).toList();

        getService.invokeAll(getTasks);
        pushService.invokeAll(pushTasks);


        List<StatsCollector.StatResult> result = statsCollector.stats();

        pushService.shutdown();
        getService.shutdown();
        statsCollector.close();

        assertThat(result)
            .hasSize(n)
            .containsOnly(expected);
    }
}
