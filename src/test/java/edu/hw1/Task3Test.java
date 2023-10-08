package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    @DisplayName("Простая вложенность")
    void simpleNesting() {
        int[] firstArray = {1, 2};
        int[] secondArray = {0, 3};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("Одинаковые массивы")
    void equalNesting() {
        int[] firstArray = {0, 1, 2, 3};
        int[] secondArray = {0, 1, 2, 3};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("Одинаковый максимальный элемент массивов")
    void maxEqualNesting() {
        int[] firstArray = {2, 3};
        int[] secondArray = {1, 2, 3};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("Одинаковый минимальный элемент массивов")
    void minEqualNesting() {
        int[] firstArray = {1, 2};
        int[] secondArray = {1, 2, 3};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("Отрицательный минимальный элемент массивов")
    void negMinNesting() {
        int[] firstArray = {0, -5, 1, 2};
        int[] secondArray = {2, 3, -7};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("Отрицательные массивы")
    void negNesting() {
        int[] firstArray = {-10, -5, -3, -2};
        int[] secondArray = {-1, -20, -7};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("Отрицательные массивы с одинаковым максимумом")
    void negMaxEqualNesting() {
        int[] firstArray = {-10, -5, -3, -2};
        int[] secondArray = {-2, -20, -7};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("Первый массив больше второго")
    void firstHigherNesting() {
        int[] firstArray = {100, -20, 30, -10, 1};
        int[] secondArray = {90, 50, 30};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("Массивы не пересекаются")
    void notIntersectNesting() {
        int[] firstArray = {30, 100, 20};
        int[] secondArray = {1, 3, 5, 8, 13};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("Максимум одного равен минимуму другого")
    void maxMinNesting() {
        int[] firstArray = {30, 100, 20};
        int[] secondArray = {349, 200, 100};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isEqualTo(false);
    }
}
