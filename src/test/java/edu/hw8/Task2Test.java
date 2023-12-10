package edu.hw8;

import edu.hw8.Task2.FixedThreadPool;
import edu.hw8.Task2.Task2;
import edu.hw8.Task2.ThreadPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {

    @Test
    @DisplayName("0 потоков")
    void create_shouldReturnNullForZeroThreadPool() {
        ThreadPool threadPool = FixedThreadPool.create(0);

        assertThat(threadPool)
            .isNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 5, 10, 20, 50, 250})
    @DisplayName("Инкрементирование числа c n потоками")
    void pool_shouldIncrementNumberInEveryThreadForNThreads(int n) throws Exception {
        AtomicInteger num = new AtomicInteger(0);

        try (ThreadPool threadPool = FixedThreadPool.create(n)) {
            threadPool.start();
            for (int i = 0; i < n; ++i) {
                threadPool.execute(num::incrementAndGet);
            }
        }

        assertThat(num.get())
            .isEqualTo(n);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 10, 20, 250})
    @DisplayName("Инкрементирование числа c n / 2 потоками")
    void pool_shouldIncrementNumberInEveryThreadForHalfNThreads(int n) throws Exception {
        AtomicInteger num = new AtomicInteger(0);

        try (ThreadPool threadPool = FixedThreadPool.create(n / 2)) {
            threadPool.start();
            for (int i = 0; i < n; ++i) {
                threadPool.execute(num::incrementAndGet);
            }
        }

        assertThat(num.get())
            .isEqualTo(n);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 10, 20, 40})
    @DisplayName("Подсчет чисел фибоначи")
    void pool_shouldCountNFibNumber(int n) throws Exception {
        List<Long> numbers = new Vector<>();

        try (ThreadPool threadPool = FixedThreadPool.create(n / 2)) {
            threadPool.start();
            for (int i = n; i >= 0; --i) {
                int finalI = i;
                threadPool.execute(() -> numbers.add(Task2.fib(finalI)));
            }
        }
        numbers.sort(Comparator.naturalOrder());

        for (int i = 2; i < n; ++i) {
            assertThat(numbers.get(i))
                .isEqualTo(numbers.get(i - 1) + numbers.get(i - 2));
        }
    }
}
