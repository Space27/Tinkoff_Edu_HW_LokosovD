package edu.hw1;

public final class Task3 {

    private Task3() {
    }

    private static int arrMin(int[] arr) {
        int min = arr[0];

        for (int i : arr) {
            if (i < min) {
                min = i;
            }
        }

        return min;
    }

    private static int arrMax(int[] arr) {
        int max = arr[0];

        for (var i : arr) {
            if (i > max) {
                max = i;
            }
        }

        return max;
    }

    public static boolean isNestable(int[] firstArr, int[] secondArr) {
        return arrMin(firstArr) > arrMin(secondArr) && arrMax(firstArr) < arrMax(secondArr);
    }
}
