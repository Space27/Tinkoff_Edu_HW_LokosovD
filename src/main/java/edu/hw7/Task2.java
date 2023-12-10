package edu.hw7;

import java.util.stream.IntStream;

public final class Task2 {

    private Task2() {
    }

    public static long factorial(int k) {
        return IntStream
            .rangeClosed(2, k)
            .parallel()
            .reduce(1, (x, y) -> x * y);
    }
}
