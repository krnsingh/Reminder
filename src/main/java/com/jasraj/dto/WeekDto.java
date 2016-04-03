package com.jasraj.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WeekDto implements Serializable {
    private List<DayDto> dates = new ArrayList<DayDto>(7);
    private int weekOfMonth;

    public List<DayDto> getDates() {
        return dates;
    }

    public WeekDto setDates(List<LocalDate> dates) {
        this.dates = dates.stream().map(date -> new DayDto().setLocalDate(date)).collect(Collectors.toList());
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
