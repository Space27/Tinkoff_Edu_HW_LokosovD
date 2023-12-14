package edu.hw11.Task3;

public final class Fib {

    private Fib() {
    }

    public static long fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 2) + fib(n - 1);
    }
}
