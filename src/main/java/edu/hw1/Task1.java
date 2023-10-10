package edu.hw1;

public final class Task1 {

    static final int SECONDS_IN_MINUTE = 60;

    private Task1() {
    }

    public static int minutesToSeconds(String time) {
        if (time == null || !time.matches("\\d{2,}:\\d{2}")) {
            return -1;
        }

        String[] splitTime = time.split(":");
        int minutes = Integer.parseInt(splitTime[0]);
        int seconds = Integer.parseInt(splitTime[1]);

        if (minutes < 0 || seconds < 0 || seconds >= SECONDS_IN_MINUTE) {
            return -1;
        }

        return SECONDS_IN_MINUTE * minutes + seconds;
    }
}
