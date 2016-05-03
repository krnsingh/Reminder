package com.jasraj.service;

import com.jasraj.dto.MonthDto;
import com.jasraj.dto.WeekDto;
import com.jasraj.entity.Alert;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarService {

    public static MonthDto populateMonth(LocalDateTime referenceDate, List<Alert> alerts) {
        List<LocalDateTime> monthDates = new ArrayList<>(31);
        LocalDateTime start = referenceDate.withDayOfMonth(1);
        LocalDateTime end = referenceDate.withDayOfMonth(referenceDate.getMonth().maxLength());

        // week starts with monday
        if (start.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
            for (LocalDateTime loopDate = start; loopDate.isBefore(end); loopDate = loopDate.plusDays(1)) {
                monthDates.add(loopDate);
            }
        } else {
            switch (start.getDayOfWeek()) {
                case TUESDAY:
                    addEmpty(monthDates, 1);
                    break;
                case WEDNESDAY:
                    addEmpty(monthDates, 2);
                    break;
                case THURSDAY:
                    addEmpty(monthDates, 3);
                    break;
                case FRIDAY:
                    addEmpty(monthDates, 4);
                    break;
                case SATURDAY:
                    addEmpty(monthDates, 5);
                    break;
                case SUNDAY:
                    addEmpty(monthDates, 6);
            }
            // TODO: move it to a different method.
            for (LocalDateTime loopDate = start; loopDate.isBefore(end); loopDate = loopDate.plusDays(1)) {
                monthDates.add(loopDate);
            }
        }
        return splitListMap(monthDates, alerts).setFirstDate(start);
    }

    private static MonthDto splitListMap(List<LocalDateTime> monthDates, List<Alert> alerts) {
        int splitSize = 7;
        MonthDto monthDto = new MonthDto();
        for (int i = 0, weekNum = 1; i < monthDates.size(); i = i + splitSize, weekNum++) {

            List<LocalDateTime> week = monthDates.subList(i, i + splitSize > monthDates.size() ?
                    monthDates.size() : i + splitSize);
            monthDto.addWeek(new WeekDto().setDates(week, alerts).setWeekOfMonth(weekNum));
        }
        return monthDto;

    }

    private static void addEmpty(List<LocalDateTime> monthDates, int numOfDays) {
        for (int i = 0; i < numOfDays; i++) {
            monthDates.add(null);
        }
    }


}
