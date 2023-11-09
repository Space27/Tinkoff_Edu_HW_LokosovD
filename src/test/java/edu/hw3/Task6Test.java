package edu.hw3;

import edu.hw3.Task6.PriorityQueueStockMarket;
import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StockMarket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class Task6Test {

    @ParameterizedTest
    @MethodSource("provideOneStock")
    @DisplayName("Добавление одной акции")
    void priorityQueueStockMarket_shouldAddAndReturnTheSameStock(Stock stock) {
        StockMarket stockMarket = new PriorityQueueStockMarket();

        stockMarket.add(stock);

        assertThat(stockMarket.mostValuableStock())
            .isEqualTo(stock);
    }

    @ParameterizedTest
    @MethodSource("provideSeveralStock")
    @DisplayName("Добавление нескольких акций")
    void priorityQueueStockMarket_shouldReturnTheMostValuableStockIfAddedMoreStocks(Stock[] stocks, Stock expected) {
        StockMarket stockMarket = new PriorityQueueStockMarket();

        for (Stock i : stocks) {
            stockMarket.add(i);
        }

        assertThat(stockMarket.mostValuableStock())
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideSeveralStockAndSecondMax")
    @DisplayName("Добавление нескольких акций и выталкивание двух максимумов")
    void priorityQueueStockMarket_shouldReturnTheMostValuableStockIfAddedMoreStocksThenRemovedMax(
        Stock[] stocks,
        Stock max,
        Stock expected
    ) {
        StockMarket stockMarket = new PriorityQueueStockMarket();

        for (Stock i : stocks) {
            stockMarket.add(i);
        }
        stockMarket.remove(max);

        assertThat(stockMarket.mostValuableStock())
            .isEqualTo(expected);
    }

    private static Stream<Arguments> provideOneStock() {
        return Stream.of(
            Arguments.of(
                new Stock(0, "Bebsi")
            ),
            Arguments.of(
                new Stock(100, "Shmesla")
            ),
            Arguments.of(
                new Stock(-100, "Puckdock")
            )
        );
    }

    private static Stream<Arguments> provideSeveralStock() {
        return Stream.of(
            Arguments.of(
                new Stock[] {new Stock(0, "Bebsi"), new Stock(10, ".com"), new Stock(5, ".net")},
                new Stock(10, ".com")
            ),
            Arguments.of(
                new Stock[] {new Stock(0, "Bebsi"), new Stock(10, ".com")},
                new Stock(10, ".com")
            ),
            Arguments.of(
                new Stock[] {new Stock(10, "Bebsi"), new Stock(0, ".com")},
                new Stock(10, "Bebsi")
            ),
            Arguments.of(
                new Stock[] {new Stock(10, "Bebsi"), new Stock(0, ".com"), new Stock(-10, "Grape"),
                    new Stock(20, "Googol"), new Stock(1, "IBF")},
                new Stock(20, "Googol")
            )
        );
    }

    private static Stream<Arguments> provideSeveralStockAndSecondMax() {
        return Stream.of(
            Arguments.of(
                new Stock[] {new Stock(0, "Bebsi"), new Stock(10, ".com"), new Stock(5, ".net")},
                new Stock(10, ".com"),
                new Stock(5, ".net")
            ),
            Arguments.of(
                new Stock[] {new Stock(0, "Bebsi"), new Stock(10, ".com")},
                new Stock(10, ".com"),
                new Stock(0, "Bebsi")
            ),
            Arguments.of(
                new Stock[] {new Stock(10, "Bebsi"), new Stock(0, ".com")},
                new Stock(10, "Bebsi"),
                new Stock(0, ".com")
            ),
            Arguments.of(
                new Stock[] {new Stock(10, "Bebsi"), new Stock(0, ".com"), new Stock(-10, "Grape"),
                    new Stock(20, "Googol"), new Stock(1, "IBF")},
                new Stock(20, "Googol"),
                new Stock(10, "Bebsi")
            )
        );
    }
}
