package edu.hw1;

public final class Task2 {
    private Task2() {
    }

    public static short countDigits(int digitCountingInput) {
        final int radix = 10;
        short count = 0;

        do {
            ++count;
            digitCountingInput /= radix;
        } while (digitCountingInput != 0);
        /* ещё можно было бы использовать десятичный логарифм по модулю числа (не учитывая 0) */

        return count;
    }
}
