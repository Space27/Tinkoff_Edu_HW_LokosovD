package edu.project4;

import edu.project4.Transformations.ColorTransformation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.assertThat;

class OneThreadRendererTest {

    private final OneThreadRenderer renderer = new OneThreadRenderer();

    @Test
    @DisplayName("Выдача случайного элемента списка")
    void getRandomElementInList_shouldReturnNotNullElementFromList() {
        List<Integer> list = IntStream.range(0, 10).boxed().toList();
        Set<Integer> hitInt = new HashSet<>();

        for (int i = 0; i < 250; ++i) {
            Integer result = renderer.getRandomElementInList(list);
            hitInt.add(result);

            assertThat(result)
                .isNotNull()
                .isIn(list);
        }

        assertThat(hitInt)
            .hasSize(list.size());
    }

    @Test
    @DisplayName("Выдача случайной точки в зоне")
    void getRandomPointInArea_shouldReturnNotNullPointInArea() {
        Area area = new Area(-1, -1, 2, 2);

        for (int i = 0; i < 250; ++i) {
            Point result = renderer.getRandomPointInArea(area);

            assertThat(result)
                .isNotNull();
            assertThat(area.contains(result))
                .isTrue();
        }
    }

    @Test
    @DisplayName("Поворот вектора")
    void rotate_shouldCorrectlyRotatePoint() {
        Point point = new Point(1, 0);

        point = renderer.rotate(point, Math.PI / 6);
        point = renderer.rotate(point, Math.PI / 2);
        point = renderer.rotate(point, Math.PI / 3);

        Point expected = new Point(-1, 0);
        assertThat(point.x())
            .isEqualTo(expected.x());
        assertThat(point.y())
            .isEqualTo(expected.y());
    }

    @Test
    @DisplayName("Закрашивание пикселя с одинарной симметрией")
    void colorSymmetryPixels_shouldColorizeOnePixelWithSymmetry1() {
        Image image = new Image(10, 10);
        Area area = new Area(-1, -1, 2, 2);
        Point point = new Point(0, 0);
        ColorTransformation color = new ColorTransformation(255, 255, 255);
        Pixel expected = new Pixel(255, 255, 255);
        expected.incHitCount();

        renderer.colorSymmetryPixels(image, point, area, color);

        assertThat(image.getPixel(5, 5))
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Закрашивание пикселя с двойной симметрией")
    void colorSymmetryPixels_shouldColorizeOnePixelWithSymmetry2() {
        OneThreadRenderer renderer2Sym = new OneThreadRenderer(new SecureRandom(), 2);
        Image image = new Image(10, 10);
        Area area = new Area(-1, -1, 2, 2);
        Point point = new Point(1, 1);
        ColorTransformation color = new ColorTransformation(255, 255, 255);
        Pixel expected = new Pixel(255, 255, 255);
        expected.incHitCount();

        renderer2Sym.colorSymmetryPixels(image, point, area, color);

        assertThat(image.getPixel(9, 9))
            .isEqualTo(expected);
        assertThat(image.getPixel(0, 0))
            .isEqualTo(expected);
    }
}
