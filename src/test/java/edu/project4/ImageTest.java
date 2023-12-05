package edu.project4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImageTest {

    @ParameterizedTest
    @CsvSource({"0,0", "-1,2", "2,-1", "0,1", "1,0"})
    @DisplayName("Некорректные параметры конструктора")
    void image_shouldReturnNullForIncorrectArguments(int width, int height) {
        assertThrows(IllegalArgumentException.class, () -> new Image(width, height));
    }

    @ParameterizedTest
    @CsvSource({"2,2", "1,1", "1,2", "2,1", "10,11"})
    @DisplayName("Проверка get")
    void get_shouldReturnActualField(int width, int height) {
        Image image = new Image(width, height);

        assertThat(image.getHeight())
            .isEqualTo(height);
        assertThat(image.getWidth())
            .isEqualTo(width);
    }

    @ParameterizedTest
    @CsvSource({"2,2,1,0", "1,1,0,0", "1,2,0,1", "2,1,0,0", "10,11,9,10"})
    @DisplayName("Взятие пикселя по корректным координатам")
    void getPixel_shouldReturnPixelForCorrectCoordinates(int width, int height, int x, int y) {
        Image image = new Image(width, height);

        assertThat(image.getPixel(x, y))
            .isNotNull();
    }

    @ParameterizedTest
    @CsvSource({"2,2,2,0", "1,1,0,1", "1,2,1,1", "2,1,1,-1", "10,11,10,9"})
    @DisplayName("Взятие пикселя по некорректным координатам")
    void getPixel_shouldReturnNullForInCorrectCoordinates(int width, int height, int x, int y) {
        Image image = new Image(width, height);

        assertThat(image.getPixel(x, y))
            .isNull();
    }
}
