package com.aiden.librarysoftwarefx.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManager {
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static String dateToString(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static Date stringToDate(String date) throws ParseException {
        return DATE_FORMAT.parse(date);
    }
    public static boolean comesOnOrBefore(Date date1, Date date2) {
        return date1.compareTo(date2) <= 0;
    }
    public static boolean comesOnOrAfter(Date date1, Date date2) {
        return date1.compareTo(date2) >= 0;
    }
    public static boolean comesOnOrBefore(String date1, String date2) throws ParseException {
        return stringToDate(date1).compareTo(stringToDate(date2)) <= 0;
    }
    public static boolean comesOnOrAfter(String date1, String date2) throws ParseException {
        return stringToDate(date1).compareTo(stringToDate(date2)) >= 0;
    }
}
