package com.jasraj.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeekDto implements Serializable {
    private List<LocalDate> dates = new ArrayList<LocalDate>(7);
    private int weekOfMonth;

    public List<LocalDate> getDates() {
        return dates;
    }

    public WeekDto setDates(List<LocalDate> dates) {
        this.dates = dates;
        return this;
    }

    public int getWeekOfMonth() {
        return weekOfMonth;
    }

    public WeekDto setWeekOfMonth(int weekOfMonth) {
        this.weekOfMonth = weekOfMonth;
        return this;
    }
}
