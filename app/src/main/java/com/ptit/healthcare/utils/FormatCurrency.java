package com.ptit.healthcare.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatCurrency {

    public static String formatCurrencyVN(int number) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        return numberFormat.format(number);
    }
}
