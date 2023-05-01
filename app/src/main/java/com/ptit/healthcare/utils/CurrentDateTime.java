package com.ptit.healthcare.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDateTime {
    public static String getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatter.format(date);
    }

    public static String getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        Date date = new Date();
        return formatter.format(date);
    }
}