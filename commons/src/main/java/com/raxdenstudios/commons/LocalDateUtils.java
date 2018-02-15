package com.raxdenstudios.commons;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;

import java.util.Calendar;

/**
 * Created by Angel on 27/09/2017.
 */

public class LocalDateUtils {

    public static Calendar toCalendar(LocalDate localDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(toMiliseconds(localDate));
        return calendar;
    }

    public static long toMiliseconds(LocalDate localDate) {
        return localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long toMiliseconds(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static LocalDate toLocalDate(long milliseconds) {
        return Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(long milliseconds) {
        return Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
