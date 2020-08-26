package com.example.poi.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date toDate(String formattedDate, String pattern) {
        Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            date = format.parse(formattedDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String formatDate(Date date, String pattern) {
        String formattedDate = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            formattedDate = format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formattedDate;
    }
}
