package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("LSP")
    void area_ShouldReturnRectangleArea(Rectangle rect) {
        Rectangle curRect = rect;

        curRect = curRect.setWidth(20);
        curRect = curRect.setHeight(10);

        assertThat(curRect.area())
            .isEqualTo(200.0);
    }

    @ParameterizedTest
    @CsvSource({"10, 10", "10, 20", "0, 20"})
    @DisplayName("Прямоугольник")
    void area_ShouldReturnOnlyRectangleArea(int width, int height) {
        Rectangle curRect = new Rectangle();

        curRect = curRect.setWidth(width);
        curRect = curRect.setHeight(height);

        assertThat(curRect.area())
            .isEqualTo(width * height);
    }

    @ParameterizedTest
    @CsvSource({"10", "20", "0"})
    @DisplayName("Квадрат с учетом прямоугольника")
    void area_ShouldReturnSquareArea(int size) {
        Rectangle curRect = new Square();

        curRect = curRect.setWidth(size);

        assertThat(curRect.area())
            .isEqualTo(size * size);
    }

    @ParameterizedTest
    @CsvSource({"10, 20", "20, 10", "0, 10"})
    @DisplayName("Квадрат с изменением сторон")
    void area_ShouldReturnSquareAreaIfItChanged(int firstSize, int secondSize) {
        Square curSquare = new Square();

        curSquare.setWidth(firstSize);
        curSquare.setHeight(secondSize);

        assertThat(curSquare.area())
            .isEqualTo(secondSize * secondSize);
    }

    @ParameterizedTest
    @CsvSource({"10, 20", "20, 10", "0, 10"})
    @DisplayName("Квадрат с прямоугольником")
    void area_ShouldReturnSquareAndRectangleAreas(int firstSize, int secondSize) {
        Square curSquare = new Square();
        Rectangle curRect;

        curSquare.setHeight(firstSize);
        curRect = curSquare.setWidth(secondSize);

        assertThat(curRect.area())
            .isEqualTo(firstSize * secondSize);
    }
}
