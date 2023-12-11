package edu.project4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

class AreaTest {

    @ParameterizedTest
    @CsvSource({"0,0,0,0", "1,1,0,0", "1,1,1,1", "0,0,1,1", "-1,1,2,0"})
    @DisplayName("Максимальное значение")
    void max_shouldReturnCordPlusLen(double x, double y, double width, double height) {
        Area area = new Area(x, y, width, height);

        assertThat(area.xMax())
            .isEqualTo(x + width);
        assertThat(area.yMax())
            .isEqualTo(y + height);
    }

    @Test
    @DisplayName("Включение точки в диапазон")
    void contains_shouldReturnTrueForPointsInArea() {
        Area area = new Area(-2, -3, 3, 2);

        assertThat(area.contains(new Point(-2, -3)))
            .isTrue();
        assertThat(area.contains(new Point(1, -1)))
            .isTrue();
    }

    @Test
    @DisplayName("Невключение точки в диапазон")
    void contains_shouldReturnFalseForPointsNotInArea() {
        Area area = new Area(-2, -3, 3, 2);

        assertThat(area.contains(new Point(-2, -3.0000001)))
            .isFalse();
        assertThat(area.contains(new Point(1.00001, -1)))
            .isFalse();
    }
}
