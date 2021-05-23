package service;

import java.time.LocalDate;

public class TimeUtil {
    public static boolean isBetweenHalfOpen(LocalDate lt, LocalDate startTime, LocalDate endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }
}
