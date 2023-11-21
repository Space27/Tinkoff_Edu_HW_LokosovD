package edu.hw5;

import java.util.regex.Pattern;

public final class Task7 {

    private Task7() {
    }

    public static boolean containsAtLeast3CharsAndThirdCharIs0(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("[01]{2}0[01]*");

        return pattern.matcher(string).matches();
    }

    public static boolean startsAndEndsSameTheSameChar(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("(0[01]*0)|(1[01]*1)|0|1|");

        return pattern.matcher(string).matches();
    }

    public static boolean lenIsMoreThan1ButLowerThan3(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("[01]{1,3}");

        return pattern.matcher(string).matches();
    }
}
