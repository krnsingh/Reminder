package com.jasraj.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class CalendarDto implements Serializable {

    private LocalDate date;
    private int dayOfMonth;

    public LocalDate getDate() {
        return date;
    }

    public CalendarDto setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public CalendarDto setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
        return this;
    }

}
