package edu.hw10.Task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10, 20})
    @DisplayName("Сравнение результатов прокси и оригинала")
    void cache_shouldWorkSameAsOriginal(int num) {
        Calculator calculator = new StandardCalc();

        Calculator proxy = CacheProxy.create(calculator, calculator.getClass());

        long result = proxy.fib(num);
        long expected = calculator.fib(num);

        assertThat(result)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10, 20})
    @DisplayName("Создание файла с результатами")
    void cache_shouldCacheAndSaveReturnValues(int num) throws IOException {
        Path path = Path.of("cache").resolve("StandardCalc").resolve("fib.txt");
        Calculator calculator = new StandardCalc();

        Calculator proxy = CacheProxy.create(calculator, calculator.getClass());

        for (int i = 0; i <= num; ++i) {
            proxy.fib(i);

            assertThat(path)
                .exists();

            List<String> lines = Files.readAllLines(path);
            for (int j = 0; j <= i; ++j) {
                assertThat(Long.valueOf(lines.get(j)))
                    .isEqualTo(calculator.fib(j));
            }
        }
    }

    @Test
    @DisplayName("Кэширование значений")
    void cache_shouldCacheReturnValues() {
        CallCounter fibCalculator = new CallCounter(new StandardCalc());

        Calculator proxy = CacheProxy.create(fibCalculator, StandardCalc.class);

        proxy.fib(5);

        assertThat(proxy.fib(5))
            .isEqualTo(5);
        assertThat(fibCalculator.getCallCount())
            .isOne();
    }

    @Test
    @DisplayName("Вызов метода без аннотации")
    void cache_shouldNotCacheMethodsWithoutAnnotation() {
        CallCounter fibCalculator = new CallCounter(new StandardCalc());

        Calculator proxy = CacheProxy.create(fibCalculator, StandardCalc.class);

        proxy.factorial(5);
        proxy.factorial(5);

        assertThat(fibCalculator.getCallCount())
            .isEqualTo(2);
    }
}
