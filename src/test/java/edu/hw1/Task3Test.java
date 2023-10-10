package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    @DisplayName("Одинаковые массивы")
    void isNestable_ShouldReturnFalseIfArraysAreEqual() {
        int[] firstArray = {0, 1, 2, 3};
        int[] secondArray = {0, 1, 2, 3};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isFalse();
    }

    @Test
    @DisplayName("Одинаковый максимальный элемент массивов")
    void isNestable_ShouldReturnFalseIfMaxesAreEqual() {
        int[] firstArray = {2, 3};
        int[] secondArray = {1, 2, 3};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isFalse();
    }

    @Test
    @DisplayName("Одинаковый минимальный элемент массивов")
    void isNestable_ShouldReturnFalseIfMinesAreEqual() {
        int[] firstArray = {1, 2};
        int[] secondArray = {1, 2, 3};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isFalse();
    }

    @Test
    @DisplayName("Отрицательный минимальный элемент массивов")
    void isNestable_ShouldOperateIfMinIsNegative() {
        int[] firstArray = {0, -5, 1, 2};
        int[] secondArray = {2, 3, -7};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isTrue();
    }

    @Test
    @DisplayName("Отрицательные массивы")
    void isNestable_ShouldOperateWithNegativeArrays() {
        int[] firstArray = {-10, -5, -3, -2};
        int[] secondArray = {-1, -20, -7};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isTrue();
    }

    @Test
    @DisplayName("Отрицательные массивы с одинаковым максимумом")
    void isNestable_ShouldReturnFalseIfNegativeMaxesAreEqual() {
        int[] firstArray = {-10, -5, -3, -2};
        int[] secondArray = {-2, -20, -7};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isFalse();
    }

    @Test
    @DisplayName("Первый массив больше второго")
    void isNestable_ShouldReturnFalseIfFirstArrayHigherThenSecond() {
        int[] firstArray = {100, -20, 30, -10, 1};
        int[] secondArray = {90, 50, 30};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isFalse();
    }

    @Test
    @DisplayName("Массивы не пересекаются")
    void isNestable_ShouldReturnFalseIfArraysNotIntersect() {
        int[] firstArray = {30, 100, 20};
        int[] secondArray = {1, 3, 5, 8, 13};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isFalse();
    }

    @Test
    @DisplayName("Максимум одного равен минимуму другого")
    void isNestable_ShouldReturnFalseIfMin1EqualsMax2() {
        int[] firstArray = {30, 100, 20};
        int[] secondArray = {349, 200, 100};

        boolean nestable = Task3.isNestable(firstArray, secondArray);

        assertThat(nestable)
            .isFalse();
    }
}
