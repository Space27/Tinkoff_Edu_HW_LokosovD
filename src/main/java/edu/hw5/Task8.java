package edu.hw5;

import java.util.regex.Pattern;

public final class Task8 {

    private Task8() {
    }

    public static boolean isLenOdd(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("([01]{2})*[01]");

        return pattern.matcher(string).matches();
    }

    public static boolean has0AtStartAndOddLenOr1AtStartAndEvenLen(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("(0|1[01])([01]{2})*");

        return pattern.matcher(string).matches();
    }

    public static boolean isZeroCountMultipleOf3(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("((1*0){3})*1*");

        return pattern.matcher(string).matches();
    }

    public static boolean anyStringExcept11And111(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("(?!(111?)$)[01]*");

        return pattern.matcher(string).matches();
    }

    public static boolean anyOddCharIs1(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("(1[01])+|1([01]1)*");

        return pattern.matcher(string).matches();
    }

    public static boolean containsAtLeastTwo0AndNoMoreThanOne1(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("1?0{2,}|0{2,}1?0*|01?0+");

        return pattern.matcher(string).matches();
    }

    public static boolean noConsecutive1(String string) {
        if (string == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("(1?0+)*[01]?");

        return pattern.matcher(string).matches();
    }
}
