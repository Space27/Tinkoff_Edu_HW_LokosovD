package edu.hw2;

import edu.hw2.Task1.Expr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0, 1, 10})
    @DisplayName("Константа")
    void Constant_ShouldKeepNum(int number) {
        var num = new Expr.Constant(number);

        assertThat(num.evaluate()).
            isEqualTo(number);
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0, 1, 10})
    @DisplayName("Изменение знака")
    void Negate_ShouldNegateConstant(int number) {
        var num = new Expr.Constant(number);

        var neg = new Expr.Negate(num);

        assertThat(neg.evaluate()).
            isEqualTo(-number);
    }

    @ParameterizedTest
    @CsvSource({"2, 5", "0, 0", "0, 1", "-2, 2", "-3, -5", "-3, 0", "2, -3", "5, -4"})
    @DisplayName("Сумма")
    void Addition_ShouldSumConstants(int first, int second) {
        var num1 = new Expr.Constant(first);
        var num2 = new Expr.Constant(second);

        var add = new Expr.Addition(num1, num2);

        assertThat(add.evaluate()).
            isEqualTo(first + second);
    }

    @ParameterizedTest
    @CsvSource({"2, 5", "0, 0", "0, 1", "-2, 2", "-3, -5", "-3, 0", "2, -3", "5, -4"})
    @DisplayName("Умножение")
    void Multiplication_ShouldMultiplyConstants(int first, int second) {
        var num1 = new Expr.Constant(first);
        var num2 = new Expr.Constant(second);

        var add = new Expr.Multiplication(num1, num2);

        assertThat(add.evaluate()).
            isEqualTo(first * second);
    }

    @ParameterizedTest
    @CsvSource({"2, 5", "0, 0", "0, 1", "-2, 2", "-3, -5", "-3, 0", "2, -3", "2, 1", "2, 2"})
    @DisplayName("Возведение в степень")
    void Exponent_ShouldMultiplyConstants(int number, int pow) {
        var num = new Expr.Constant(number);

        var add = new Expr.Exponent(num, pow);

        assertThat(add.evaluate()).
            isEqualTo(Math.pow(number, pow));
    }
}
