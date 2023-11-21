package edu.hw5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public final class Task1 {

    private Task1() {
    }

    private static final int MINUTES_IN_HOUR = 60;

    public static String getAvgSessionTime(List<String> sessionPeriods) throws IllegalArgumentException {
        if (sessionPeriods == null || sessionPeriods.isEmpty()) {
            return "0ч 0м";
        }

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd, hh:mm");
        long minutesSum = 0;

        for (String session : sessionPeriods) {
            String[] splitSession = session.split(" - ");

            if (splitSession.length == 2) {
                try {
                    Instant start = simpleDateFormat.parse(splitSession[0]).toInstant();
                    Instant end = simpleDateFormat.parse(splitSession[1]).toInstant();

                    Duration duration = Duration.between(start, end);

                    if (!duration.isNegative()) {
                        minutesSum += duration.toMinutes();
                    } else {
                        throw new IllegalArgumentException("Second date must be higher than first");
                    }
                } catch (ParseException e) {
                    throw new IllegalArgumentException("Date must match to pattern yyyy-MM-dd, hh:mm");
                }
            } else {
                throw new IllegalArgumentException("Period must contain two dates");
            }
        }

        long resultMinutes = minutesSum / sessionPeriods.size();
        return String.format("%dч %dм", resultMinutes / MINUTES_IN_HOUR, resultMinutes % MINUTES_IN_HOUR);
    }
}
