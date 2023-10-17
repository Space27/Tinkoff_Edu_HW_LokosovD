package edu.hw3;

public final class Task1 {

    private Task1() {
    }

    private static boolean isLatin(char letter) {
        return (int) letter >= (int) 'a' && (int) letter <= (int) 'z'
            || (int) letter >= (int) 'A' && (int) letter <= (int) 'Z';
    }

    public static String atbash(String string) {
        if (string == null) {
            return null;
        }

        char[] charArr = string.toCharArray();
        char endLetter;
        char startLetter;

        for (int i = 0; i < charArr.length; ++i) {
            if (Character.isAlphabetic(charArr[i]) && isLatin(charArr[i])) {
                endLetter = Character.isLowerCase(charArr[i]) ? 'z' : 'Z';
                startLetter = Character.isLowerCase(charArr[i]) ? 'a' : 'A';
                charArr[i] = (char) ((int) startLetter + (int) endLetter - (int) charArr[i]);
            }
        }

        return String.valueOf(charArr);
    }
}
