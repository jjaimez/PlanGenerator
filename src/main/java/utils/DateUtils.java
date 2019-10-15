package utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date addMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }
}
