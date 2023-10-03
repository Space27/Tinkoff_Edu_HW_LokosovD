package edu.hw1;

public final class Task1 {
    public static int minutesToSeconds(String time) throws NullPointerException {
        final int secondsInMinute = 60;
        final int minutes;
        final int seconds;
        String[] splitTime = time.split(":");

        if (splitTime.length != 2 || splitTime[0].length() < 2 || splitTime[1].length() != 2) {
            return -1;
        }

        try {
            minutes = Integer.parseInt(splitTime[0]);
            seconds = Integer.parseInt(splitTime[1]);
        } catch (NumberFormatException ex) {
            return -1;
        }

        if (minutes < 0 || seconds < 0 || seconds >= secondsInMinute) {
            return -1;
        }

        return secondsInMinute * minutes + seconds;
    }
}
