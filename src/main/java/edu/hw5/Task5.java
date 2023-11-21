package edu.hw5;

import java.util.regex.Pattern;

public final class Task5 {

    private Task5() {
    }

    public static boolean validateRegistrationMark(String registrationMark) {
        if (registrationMark == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУ]{2}\\d{2,3}");

        return pattern.matcher(registrationMark).matches();
    }
}
