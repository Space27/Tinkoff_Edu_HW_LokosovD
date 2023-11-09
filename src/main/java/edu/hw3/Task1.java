package edu.hw3;

public final class Task1 {

    private Task1() {
    }

    private static boolean isLatin(char letter) {
        return letter >= 'a' && letter <= 'z' || letter >= 'A' && letter <= 'Z';
    }

    public static String atbash(String string) {
        if (string == null) {
            return null;
        }

        char[] charArr = string.toCharArray();
        char endLetter;
        char startLetter;

        for (int i = 0; i < charArr.length; ++i) {
            if (isLatin(charArr[i])) {
                endLetter = Character.isLowerCase(charArr[i]) ? 'z' : 'Z';
                startLetter = Character.isLowerCase(charArr[i]) ? 'a' : 'A';
                charArr[i] = (char) (startLetter + endLetter - charArr[i]);
            }
        }

        return String.valueOf(charArr);
    }
}
