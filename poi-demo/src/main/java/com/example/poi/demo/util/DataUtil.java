package com.example.poi.demo.util;

import java.text.DecimalFormat;

public class DataUtil {
    public static String formatDecimal(double value, String pattern) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            return decimalFormat.format(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(value);
    }
}
