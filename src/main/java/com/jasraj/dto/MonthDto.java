package com.jasraj.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MonthDto implements Serializable {

    private List<WeekDto> weeks = new ArrayList<WeekDto>(5);
    private String monthName;
    private LocalDate firstDate;

    public List<WeekDto> getWeeks() {
        return weeks;
    }

    public void addWeek(WeekDto week) {
        this.weeks.add(week);
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public LocalDate getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(LocalDate firstDate) {
        this.firstDate = firstDate;
    }
}