package com.senla.util.utilits;

import java.sql.Timestamp;
import java.time.LocalDate;

public class DataTimeUtil {

    private DataTimeUtil() {
    }

    public static Timestamp localDataToTimestamp(LocalDate localDate){
        return Timestamp.valueOf(localDate.atStartOfDay());
    }

    public static LocalDate timestampToLocalDate(Timestamp timestamp){
        return timestamp.toLocalDateTime().toLocalDate();
    }
}
