package com.ptit.healthcare.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateField {

    public static boolean validateEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        String regex = "^(84|0[3|5|7|8|9])+([0-9]{8})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean validatePassword(String password) {
        return true;
//        return !(password.length() < 5);
    }
}
