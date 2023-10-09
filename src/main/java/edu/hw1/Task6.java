package edu.hw1;

import java.util.Arrays;

public final class Task6 {

    static final int K_CONST = 6174;

    private Task6() {
    }

    private static int[] createDigitArr(int number) {
        final int radix = 10;
        int numberCpy = number;
        int[] digitArr = new int[Task2.countDigits(numberCpy)];

        for (int i = 0; i < digitArr.length; ++i) {
            digitArr[i] = numberCpy % radix;
            numberCpy /= radix;
        }

        return digitArr;
    }

    private static int createNumFromArr(int[] digitArr) {
        final int radix = 10;
        int number = 0;

        for (int j : digitArr) {
            number *= radix;
            number += j;
        }

        return number;
    }

    private static void reverseArr(int[] arr) {
        for (int i = 0; i < arr.length / 2; ++i) {
            arr[i] ^= arr[arr.length - i - 1];
            arr[arr.length - i - 1] ^= arr[i];
            arr[i] ^= arr[arr.length - i - 1];
        }
    }

    public static int countK(int number) {
        final int min = 1000;
        final int max = 10000;
        final int divisorOfNumWithSameDigits = 1111;

        if (number == K_CONST) {
            return 0;
        }
        if (number <= min || number >= max || number % divisorOfNumWithSameDigits == 0) {
            return -1;
        }

        int[] digitArr = createDigitArr(number);

        Arrays.sort(digitArr);
        int highNum = createNumFromArr(digitArr);

        reverseArr(digitArr);
        int lowNum = createNumFromArr(digitArr);

        return countK(lowNum - highNum) + 1;
    }
}
