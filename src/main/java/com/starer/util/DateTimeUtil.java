package com.starer.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateTimeUtil {

    private static final SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String convertTimestampToString(Timestamp timestamp) {
        return sdf.format(timestamp);
    }
}
