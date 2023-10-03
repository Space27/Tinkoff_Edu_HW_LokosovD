package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Простая длина видео")
    void simpleVL() {
        // given
        String videoLen = "01:00";

        // when
        int convertedVideoLen = Task1.minutesToSeconds(videoLen);

        // then
        assertThat(convertedVideoLen)
            .isEqualTo(60);
    }

    @Test
    @DisplayName("Нулевая длина видео")
    void zeroVL() {
        String videoLen = "00:00";

        int convertedVideoLen = Task1.minutesToSeconds(videoLen);

        assertThat(convertedVideoLen)
            .isEqualTo(0);
    }

    @Test
    @DisplayName("Обычная длина видео")
    void commonVL() {
        String videoLen = "12:43";

        int convertedVideoLen = Task1.minutesToSeconds(videoLen);

        assertThat(convertedVideoLen)
            .isEqualTo(12 * 60 + 43);
    }

    @Test
    @DisplayName("Большая длина видео")
    void bigVL() {
        String videoLen = "1378:59";

        int convertedVideoLen = Task1.minutesToSeconds(videoLen);

        assertThat(convertedVideoLen)
            .isEqualTo(1378 * 60 + 59);
    }

    @Test
    @DisplayName("Слишком много секунд")
    void tooManySecVL() {
        String videoLen = "00:60";

        int convertedVideoLen = Task1.minutesToSeconds(videoLen);

        assertThat(convertedVideoLen)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Отрицательные секунды в длине видео")
    void negSecVL() {
        String videoLen = "00:-01";

        int convertedVideoLen = Task1.minutesToSeconds(videoLen);

        assertThat(convertedVideoLen)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Отрицательные минуты в длине видео")
    void negMinVL() {
        String videoLen = "-01:00";

        int convertedVideoLen = Task1.minutesToSeconds(videoLen);

        assertThat(convertedVideoLen)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Мусор в длине видео")
    void garbageVL() {
        String videoLen = "01минута:00секунд";

        int convertedVideoLen = Task1.minutesToSeconds(videoLen);

        assertThat(convertedVideoLen)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Некорректная длина секунд в длине видео (меньше нужного)")
    void incorrectSecLenLowVL() {
        String videoLen = "00:0";

        int convertedVideoLen = Task1.minutesToSeconds(videoLen);

        assertThat(convertedVideoLen)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Некорректная длина секунд в длине видео (больше нужного)")
    void incorrectSecLenHighVL() {
        String videoLen = "00:000";

        int convertedVideoLen = Task1.minutesToSeconds(videoLen);

        assertThat(convertedVideoLen)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Некорректная длина секунд в длине видео (меньше нужного)")
    void incorrectMinLenVL() {
        String videoLen = "0:00";

        int convertedVideoLen = Task1.minutesToSeconds(videoLen);

        assertThat(convertedVideoLen)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Нет минут в длине видео")
    void noMinVL() {
        String videoLen = ":00";

        int convertedVideoLen = Task1.minutesToSeconds(videoLen);

        assertThat(convertedVideoLen)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Нет секунд в длине видео")
    void noSecVL() {
        String videoLen = "00:";

        int convertedVideoLen = Task1.minutesToSeconds(videoLen);

        assertThat(convertedVideoLen)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Лишние данные в длине видео")
    void extraDataVL() {
        String videoLen = "01:01:01";

        int convertedVideoLen = Task1.minutesToSeconds(videoLen);

        assertThat(convertedVideoLen)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Нет разделителя в длине видео")
    void noSepVL() {
        String videoLen = "0101";

        int convertedVideoLen = Task1.minutesToSeconds(videoLen);

        assertThat(convertedVideoLen)
            .isEqualTo(-1);
    }
}
