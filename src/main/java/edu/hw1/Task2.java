package edu.hw1;

public final class Task2 {

    private Task2() {
    }

    public static int countDigits(int inputNumber) {
        final int radix = 10;
        int number = inputNumber;
        int count = 0;

        do {
            ++count;
            number /= radix;
        } while (number != 0);

        return count;
    }
}
