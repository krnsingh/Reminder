package com.jasraj.com.jasraj.service;

import com.jasraj.dto.MonthDto;
import com.jasraj.dto.WeekDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarService {

    public static MonthDto populateMonth(LocalDate referenceDate) {
        List<LocalDate> monthDates = new ArrayList<LocalDate>(31);
        LocalDate start = referenceDate.withDayOfMonth(1);
        LocalDate end = referenceDate.withDayOfMonth(referenceDate.lengthOfMonth());

        // week starts with monday
        if (start.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
            for (LocalDate loopDate = start; loopDate.isBefore(end); loopDate = loopDate.plusDays(1)) {
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
            for (LocalDate loopDate = start; loopDate.isBefore(end); loopDate = loopDate.plusDays(1)) {
                monthDates.add(loopDate);
            }
        }
        return splitListMap(monthDates).setFirstDate(start);
    }

    private static MonthDto splitListMap(List<LocalDate> monthDates) {
        int splitSize = 7;
        MonthDto monthDto = new MonthDto();
        for (int i = 0, weekNum = 1; i < monthDates.size(); i = i + splitSize, weekNum++) {

            List<LocalDate> week = monthDates.subList(i, i + splitSize > monthDates.size() ?
                    monthDates.size() : i + splitSize);
            monthDto.addWeek(new WeekDto().setDates(week).setWeekOfMonth(weekNum));
        }
        return monthDto;

    }

    private static void addEmpty(List<LocalDate> monthDates, int numOfDays) {
        for (int i = 0; i < numOfDays; i++) {
            monthDates.add(null);
        }
    }


}
