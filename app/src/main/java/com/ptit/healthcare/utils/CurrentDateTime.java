package com.ptit.healthcare.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CurrentDateTime {

    public static String getCurrentDateTime() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("vi", "VN"));
        String currentDate = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        return currentDate;
    }
}
