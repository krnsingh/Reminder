package com.jasraj.com.jasraj.service;

import com.jasraj.dto.CalendarDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarService {

    public static List<CalendarDto> populateMonth(LocalDate referenceDate, int plusMinusMonths) {
        List<CalendarDto> calendarDtos = new ArrayList<CalendarDto>(31);
        LocalDate date = referenceDate.plusMonths(plusMinusMonths);
        LocalDate start = date.withDayOfMonth(1);
        LocalDate end = date.withDayOfMonth(date.lengthOfMonth());
        // week starts with monday
        if (start.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
            for (LocalDate loopDate = start; loopDate.isBefore(end); loopDate.plusDays(1)) {
                calendarDtos.add(new CalendarDto().setDate(loopDate).setDayOfMonth(loopDate.getDayOfMonth()));
            }
        } else {
            switch (start.getDayOfWeek()) {
                case TUESDAY:
                    addEmpty(calendarDtos, 1);
                    break;
                case WEDNESDAY:
                    addEmpty(calendarDtos, 2);
                    break;
                case THURSDAY:
                    addEmpty(calendarDtos, 3);
                    break;
                case FRIDAY:
                    addEmpty(calendarDtos, 4);
                    break;
                case SATURDAY:
                    addEmpty(calendarDtos, 5);
                    break;
                case SUNDAY:
                    addEmpty(calendarDtos, 6);
            }
            // TODO: move it to a different method.
            for (LocalDate loopDate = start; loopDate.isBefore(end); loopDate.plusDays(1)) {
                calendarDtos.add(new CalendarDto().setDate(loopDate).setDayOfMonth(loopDate.getDayOfMonth()));
            }
        }
        return calendarDtos;
    }

    private static void addEmpty(List<CalendarDto> calendarDtos, int numOfDays) {
        for (int i = 0; i < numOfDays; i++) {
            calendarDtos.add(new CalendarDto().setDate(null).setDayOfMonth(0));
        }

    }


}
