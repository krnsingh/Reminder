package com.jasraj.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MonthDto implements Serializable {

    private List<WeekDto> weeks = new ArrayList<WeekDto>(5);
    private LocalDateTime firstDate;

    public List<WeekDto> getWeeks() {
        return weeks;
    }

    public void addWeek(WeekDto week) {
        this.weeks.add(week);
    }


    public LocalDateTime getFirstDate() {
        return firstDate;
    }

    public MonthDto setFirstDate(LocalDateTime firstDate) {
        this.firstDate = firstDate;
        return this;
    }
}