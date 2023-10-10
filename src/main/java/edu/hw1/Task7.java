package edu.hw1;

public final class Task7 {

    final static String NEGATIVE_ARG = "Number must be positive";

    private Task7() {
    }

    public static int rotateRight(int inputNumber, int inputShift) throws IllegalArgumentException {
        if (inputNumber < 0) {
            throw new IllegalArgumentException(NEGATIVE_ARG);
        }
        if (inputShift < 0) {
            rotateLeft(inputNumber, -inputShift);
        }

        int bNumLen = Integer.toBinaryString(inputNumber).length();
        int number = inputNumber;
        int shift = inputShift % bNumLen;
        int tmpMask = (int) Math.pow(2, shift) - 1; /* создаётся маска типа 0..01..1 из {len - shift} 0 и {shift} 1 */

        tmpMask &= inputNumber; /* запоминаются {shift} последних бит */
        tmpMask <<= bNumLen - shift;
        number >>= shift;
        number |= tmpMask;

        return number;
    }

    public static int rotateLeft(int inputNumber, int inputShift) throws IllegalArgumentException {
        return rotateRight(
            inputNumber,
            Integer.toBinaryString(inputNumber).length() - inputShift % Integer.toBinaryString(inputNumber).length()
        );
    }
}
