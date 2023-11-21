package edu.hw5;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Task6 {

    private Task6() {
    }

    public static boolean isSubSequence(String sub, String string) {
        if (sub == null || string == null) {
            return Objects.equals(sub, string);
        }

        StringBuilder regex = new StringBuilder();

        for (char i : sub.toCharArray()) {
            regex.append(String.format("%c.*", i));
        }

        Pattern pattern = Pattern.compile(regex.toString());

        return pattern.matcher(string).find();
    }
}
