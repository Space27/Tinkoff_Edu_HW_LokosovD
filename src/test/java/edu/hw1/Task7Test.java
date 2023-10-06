package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Простой пример сдвига вправо")
    void simpleRightNum() {
        int number = 8;
        int shift = 1;

        int shiftedNum = Task7.rotateRight(number, shift);

        assertThat(shiftedNum)
            .isEqualTo(4);
    }

    @Test
    @DisplayName("Простой пример сдвига влево")
    void simpleLeftNum() {
        int number = 16;
        int shift = 1;

        int shiftedNum = Task7.rotateLeft(number, shift);

        assertThat(shiftedNum)
            .isEqualTo(1);
    }

    @Test
    @DisplayName("Единица вправо")
    void oneRightNum() {
        int number = 1;
        int shift = 1;

        int shiftedNum = Task7.rotateRight(number, shift);

        assertThat(shiftedNum)
            .isEqualTo(1);
    }

    @Test
    @DisplayName("Единица влево")
    void oneLeftNum() {
        int number = 1;
        int shift = 1;

        int shiftedNum = Task7.rotateRight(number, shift);

        assertThat(shiftedNum)
            .isEqualTo(1);
    }

    @Test
    @DisplayName("Сдвиг на длину числа вправо")
    void shiftLikeLenRightNum() {
        int number = 19;
        int shift = 5;

        int shiftedNum = Task7.rotateRight(number, shift);

        assertThat(shiftedNum)
            .isEqualTo(19);
    }

    @Test
    @DisplayName("Сдвиг на длину числа влево")
    void shiftLikeLenLeftNum() {
        int number = 19;
        int shift = 5;

        int shiftedNum = Task7.rotateRight(number, shift);

        assertThat(shiftedNum)
            .isEqualTo(19);
    }

    @Test
    @DisplayName("Большой сдвиг вправо")
    void bigShiftRightNum() {
        int number = 53;
        int shift = 9;

        int shiftedNum = Task7.rotateRight(number, shift);

        assertThat(shiftedNum)
            .isEqualTo(46);
    }

    @Test
    @DisplayName("Большой сдвиг влево")
    void bigShiftLeftNum() {
        int number = 53;
        int shift = 8;

        int shiftedNum = Task7.rotateLeft(number, shift);

        assertThat(shiftedNum)
            .isEqualTo(23);
    }

    @Test
    @DisplayName("Сдвиг нуля вправо")
    void zeroRightNum() {
        int number = 0;
        int shift = 10;

        int shiftedNum = Task7.rotateRight(number, shift);

        assertThat(shiftedNum)
            .isEqualTo(0);
    }

    @Test
    @DisplayName("Сдвиг нуля влево")
    void zeroLeftNum() {
        int number = 0;
        int shift = 10;

        int shiftedNum = Task7.rotateLeft(number, shift);

        assertThat(shiftedNum)
            .isEqualTo(0);
    }

    @Test
    @DisplayName("Сдвиг большого числа вправо")
    void bigRightNum() {
        int number = 1853;
        int shift = 4;

        int shiftedNum = Task7.rotateRight(number, shift);

        assertThat(shiftedNum)
            .isEqualTo(1779);
    }

    @Test
    @DisplayName("Сдвиг большого числа влево")
    void bigLeftNum() {
        int number = 1853;
        int shift = 4;

        int shiftedNum = Task7.rotateLeft(number, shift);

        assertThat(shiftedNum)
            .isEqualTo(990);
    }
}
