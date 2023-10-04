package edu.hw1;

public final class Task2 {
    private Task2() {
    }

    public static short countDigits(int number) {
        short count = 0;

        do {
            ++count;
            number /= 10;
        } while (number != 0);
        /*ещё можно было бы использовать десятичный логарифм по модулю числа (не учитывая 0)*/

        return count;
    }
}
