package com.jasraj.service;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateService {

    public static boolean compare(LocalDateTime date1, LocalDateTime date2) {
        if(date1 == null || date2 == null)
            return false;
        if(date1.getDayOfMonth() == date2.getDayOfMonth()
            && date1.getMonthValue() == date2.getMonthValue()
            && date1.getYear() == date2.getYear())
            return true;
        return false;
    }
}
