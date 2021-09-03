package com.senla.util.utilits;

import java.time.LocalDate;

public class TimeBetweenUtil {
    public static boolean isBetweenHalfOpen(LocalDate lt, LocalDate startTime, LocalDate endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }
}
