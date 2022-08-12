package com.talentql.backendassessment.util;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;

public class DateValidationUtil {

    @SneakyThrows
    public static boolean isDateValid(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(format.equals(date)){
            return true;
        }
        return false;
    }
}
