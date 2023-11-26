package edu.hw8.Task2;

public final class Task2 {

    private Task2() {
    }

    public static long countNFib(int n) {
        if (n < 2) {
            return n;
        }
        return countNFib(n - 1) + countNFib(n - 2);
    }
}
