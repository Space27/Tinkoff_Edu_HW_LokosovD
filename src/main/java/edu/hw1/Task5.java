package edu.hw1;

public final class Task5 {
    private Task5() {
    }

    private static boolean isPalindrome(int number) {
        final int radix = 10;
        int rightMask = 1;
        int leftMask = (int) Math.pow(radix, Task2.countDigits(number) - 1);

        while (leftMask > rightMask) {
            if (number / leftMask % radix != number / rightMask % radix) {
                return false;
            }

            rightMask *= radix;
            leftMask /= radix;
        }

        return true;
    }

    private static int numDescendant(int inputNum) {
        /* desc(abc) = concatenate(a, (b + c)) */
        final int radix = 10;
        int numAncestor = inputNum;
        int resNum = 0;
        int curSum;
        int numAccum = 1;

        while (numAncestor != 0) {
            curSum = numAncestor % radix + numAncestor / radix % radix;
            resNum += curSum * numAccum;

            if (curSum >= radix) {
                numAccum *= radix;
            }
            numAccum *= radix;

            numAncestor /= radix * radix;
        }

        return resNum;
    }

    public static boolean isPalindromeDescendant(int number) {
        if (Task2.countDigits(number) < 2) {
            return false;
        } else if (isPalindrome(number)) {
            return true;
        }

        return isPalindromeDescendant(numDescendant(number));
    }
}
