package edu.hw1;

public final class Task8 {

    private Task8() {
    }

    private static int countKnights(int[][] chessBoard) {
        int knightCount = 0;

        for (var i : chessBoard) {
            for (var j : i) {
                if (j == 1) {
                    ++knightCount;
                }
            }
        }

        return knightCount;
    }

    private static int[][] createKnightCords(int[][] chessBoard) {
        int[][] knightCords = new int[countKnights(chessBoard)][2];
        int tmpKnightCount = 0;

        for (int i = 0; i < chessBoard.length; ++i) {
            for (int j = 0; j < chessBoard[0].length; ++j) {
                if (chessBoard[i][j] == 1) {
                    knightCords[tmpKnightCount][0] = i;
                    knightCords[tmpKnightCount][1] = j;
                    ++tmpKnightCount;
                }
            }
        }

        return knightCords;
    }

    public static boolean knightBoardCapture(int[][] chessBoard) throws IllegalArgumentException {
        final int chessBoardSize = 8;
        if (chessBoard.length != chessBoardSize || chessBoard[0].length != chessBoardSize) {
            throw new IllegalArgumentException();
        }

        int[][] knightCords = createKnightCords(chessBoard);

        for (int i = 0; i < knightCords.length; ++i) {
            for (int j = i + 1; j < knightCords.length; ++j) {
                if (Math.abs((knightCords[i][0] - knightCords[j][0]) * (knightCords[i][1] - knightCords[j][1])) == 2) {
                    return false;
                }
            }
        }

        return true;
    }
}
