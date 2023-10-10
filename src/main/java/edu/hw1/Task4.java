package edu.hw1;

public final class Task4 {

    private Task4() {
    }

    public static String fixString(String brokenStr) {
        if (brokenStr == null) {
            return null;
        }

        char[] splitStr = brokenStr.toCharArray();
        char tmpChr;

        for (int i = 1; i < splitStr.length; i += 2) {
            tmpChr = splitStr[i];
            splitStr[i] = splitStr[i - 1];
            splitStr[i - 1] = tmpChr;
        }

        return String.valueOf(splitStr);
    }
}
