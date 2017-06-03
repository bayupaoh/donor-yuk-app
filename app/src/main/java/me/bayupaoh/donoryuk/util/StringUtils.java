package me.bayupaoh.donoryuk.util;

/**
 * Created by codelabsunikom on 6/3/17.
 */

public class StringUtils {
    public static String convertCalendarToString(int yearInt, int monthOfYear, int dayOfMonth, int hourInt, int minuteInt) {
        String year = String.valueOf(yearInt);
        String month = String.valueOf(monthOfYear + 1);
        String day = String.valueOf(dayOfMonth);
        String hour = String.valueOf(hourInt);
        String minute = String.valueOf(minuteInt);

        if (monthOfYear < 10) {
            month = "0" + month;
        }

        if (dayOfMonth < 10) {
            day = "0" + day;
        }
        return   month + "/" +day + "/" +year;
    }

}
