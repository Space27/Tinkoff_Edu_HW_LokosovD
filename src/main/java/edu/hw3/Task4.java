package edu.hw3;

public final class Task4 {

    private Task4() {
    }

    private static final int MAX_ROMAN_NUM = 3999;

    public static String convertToRoman(int number) throws IllegalArgumentException {
        if (number < 0 || number > MAX_ROMAN_NUM) {
            throw new IllegalArgumentException("Number out of Roman Range");
        }
        StringBuilder result = new StringBuilder();
        int curNum = number;

        for (int curDigit = ROMAN_DIGITS.length - 1; curDigit >= 0 && curNum > 0;) {
            if (curNum >= ROMAN_DIGITS[curDigit].value) {
                result.append(ROMAN_DIGITS[curDigit].string);
                curNum -= ROMAN_DIGITS[curDigit].value;
            } else {
                --curDigit;
            }
        }

        return result.toString();
    }

    private static final Pair[] ROMAN_DIGITS =
        {new Pair(1, "I"), new Pair(4, "IV"), new Pair(5, "V"), new Pair(9, "IX"),
            new Pair(10, "X"), new Pair(40, "XL"), new Pair(50, "L"), new Pair(90, "XC"),
            new Pair(100, "C"), new Pair(400, "CD"), new Pair(500, "D"), new Pair(900, "CM"),
            new Pair(1000, "M")};

    static class Pair {
        int value;
        String string;

        Pair(int value, String string) {
            this.value = value;
            this.string = string;
        }
    }
}
