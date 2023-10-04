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
        /* ещё можно было бы использовать десятичный логарифм по модулю числа (не учитывая 0) */

        return count;
    }
}
