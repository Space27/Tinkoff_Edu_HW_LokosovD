package edu.hw8.Task2;

public final class Task2 {

    private Task2() {
    }

    public static long fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 2) + fib(n - 1);
    }
}
