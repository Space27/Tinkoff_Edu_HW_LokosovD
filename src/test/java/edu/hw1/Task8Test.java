package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {

    @Test
    @DisplayName("Коней нет")
    void knightBoardCapture_ShouldReturnFalseForNoKnights() {
        int[][] chessBoard = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        boolean isKnightJumpsKnight = Task8.knightBoardCapture(chessBoard);

        assertThat(isKnightJumpsKnight)
            .isTrue();
    }

    @Test
    @DisplayName("Все кони")
    void knightBoardCapture_ShouldReturnTrueForAllKnights() {
        int[][] chessBoard = {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
        };

        boolean isKnightJumpsKnight = Task8.knightBoardCapture(chessBoard);

        assertThat(isKnightJumpsKnight)
            .isFalse();
    }

    @Test
    @DisplayName("Один конь")
    void knightBoardCapture_ShouldReturnFalseForOneKnight() {
        int[][] chessBoard = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        boolean isKnightJumpsKnight = Task8.knightBoardCapture(chessBoard);

        assertThat(isKnightJumpsKnight)
            .isTrue();
    }

    @Test
    @DisplayName("Два нейтральных коня")
    void knightBoardCapture_ShouldReturnFalseForTwoNeutralKnights() {
        int[][] chessBoard = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        boolean isKnightJumpsKnight = Task8.knightBoardCapture(chessBoard);

        assertThat(isKnightJumpsKnight)
            .isTrue();
    }

    @Test
    @DisplayName("Два бьющих коня")
    void knightBoardCapture_ShouldReturnTrueForTwoBeatingKnights() {
        int[][] chessBoard = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        boolean isKnightJumpsKnight = Task8.knightBoardCapture(chessBoard);

        assertThat(isKnightJumpsKnight)
            .isFalse();
    }

    @Test
    @DisplayName("Кони в шахматном порядке")
    void knightBoardCapture_ShouldCaptureKnightIfTheyAreInChessOrder() {
        int[][] chessBoard = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1}
        };

        boolean isKnightJumpsKnight = Task8.knightBoardCapture(chessBoard);

        assertThat(isKnightJumpsKnight)
            .isTrue();
    }

    @Test
    @DisplayName("Сложный порядок коней")
    void knightBoardCapture_ShouldCaptureKnight() {
        int[][] chessBoard = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };

        boolean isKnightJumpsKnight = Task8.knightBoardCapture(chessBoard);

        assertThat(isKnightJumpsKnight)
            .isFalse();
    }

    @Test
    @DisplayName("Кони на границах")
    void knightBoardCapture_ShouldCaptureKnightIfItOnBorder() {
        int[][] chessBoard = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0}
        };

        boolean isKnightJumpsKnight = Task8.knightBoardCapture(chessBoard);

        assertThat(isKnightJumpsKnight)
            .isFalse();
    }

    @Test
    @DisplayName("Конь в углу")
    void knightBoardCapture_ShouldCaptureKnightIfItOnCorner() {
        int[][] chessBoard = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1}
        };

        boolean isKnightJumpsKnight = Task8.knightBoardCapture(chessBoard);

        assertThat(isKnightJumpsKnight)
            .isFalse();
    }
}
