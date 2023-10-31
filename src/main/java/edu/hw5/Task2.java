package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public final class Task2 {

    private static final int DAY = 13;

    private Task2() {
    }

    public static ArrayList<LocalDate> get13thFridaysInYear(int year) {
        ArrayList<LocalDate> listOf13thFridays = new ArrayList<>();
        LocalDate yearStart = LocalDate.ofYearDay(year, DAY);

        while (yearStart.getYear() == year) {
            if (yearStart.getDayOfWeek() == DayOfWeek.FRIDAY) {
                listOf13thFridays.add(yearStart);
            }

            yearStart = yearStart.plusMonths(1);
        }

        return listOf13thFridays;
    }

    public static LocalDate getNext13thFriday(LocalDate date) {
        LocalDate resDate = date;

        do {
            resDate = resDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        } while (resDate.getDayOfMonth() != DAY);

        return resDate;
    }
}
