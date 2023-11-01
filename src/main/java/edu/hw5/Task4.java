package edu.hw5;

import java.util.regex.Pattern;

public final class Task4 {

    private Task4() {
    }

    public static boolean validatePassword(String password) {
        if (password == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("[~!@#$%^&*|]+");

        return pattern.matcher(password).find();
    }
}
