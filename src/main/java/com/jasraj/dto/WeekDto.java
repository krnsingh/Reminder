package com.jasraj.dto;

import com.jasraj.entity.Alert;
import com.jasraj.service.DateService;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WeekDto implements Serializable {
    private List<DayDto> dates = new ArrayList<DayDto>(7);
    private int weekOfMonth;

    public List<DayDto> getDates() {
        return dates;
    }

    public WeekDto setDates(List<LocalDateTime> dates, List<Alert> alerts) {


        dates.stream().forEach(date -> {
            long count = alerts.stream().filter(alert -> DateService.compare(
                    alert.getLocalDate(),
                    date)).count();
            this.dates.add(new DayDto().setLocalDate(date).setAlertExists(count > 0));
        });
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
