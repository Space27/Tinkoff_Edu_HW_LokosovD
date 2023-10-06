package edu.hw1;

public final class Task7 {
    private Task7() {
    }

    public static int rotateRight(int inputNumber, int inputShift) throws IllegalAccessError {
        if (inputNumber < 0 || inputShift < 0) {
            throw new IllegalArgumentException();
        }

        int bNumLen = Integer.toBinaryString(inputNumber).length();
        int number = inputNumber;
        int shift = inputShift % bNumLen;
        int tmpMask = (int) Math.pow(2, shift) - 1; /* создаётся маска типа 0..01..1 из shift 1 */

        tmpMask &= inputNumber; /* запоминаются shift последних бит */
        tmpMask <<= bNumLen - shift;
        number >>= shift;
        number |= tmpMask;

        return number;
    }

    public static int rotateLeft(int inputNumber, int inputShift) throws IllegalAccessError {
        if (inputNumber < 0 || inputShift < 0) {
            throw new IllegalArgumentException();
        }

        int bNumLen = Integer.toBinaryString(inputNumber).length();
        int number = inputNumber;
        int shift = inputShift % bNumLen;
        int tmpMask = ((int) Math.pow(2, shift) - 1) << (bNumLen - shift); /* создаётся маска типа 1..10..0 */

        tmpMask &= inputNumber; /* запоминаются shift первых бит */
        number &= ~tmpMask; /* удаляются первые shift бит */
        tmpMask >>= bNumLen - shift;
        number <<= shift;
        number |= tmpMask;

        return number;
    }
}
