package edu.hw1;

public final class Task1 {
    private Task1() {
    }

    public static int minutesToSeconds(String time) {
        if (time == null || !time.matches("\\d{2,}:\\d{2}")) {
            return -1;
        }

        final int secondsInMinute = 60;

        String[] splitTime = time.split(":");
        int minutes = Integer.parseInt(splitTime[0]);
        int seconds = Integer.parseInt(splitTime[1]);

        if (minutes < 0 || seconds < 0 || seconds >= secondsInMinute) {
            return -1;
        }

        return secondsInMinute * minutes + seconds;
    }
}
