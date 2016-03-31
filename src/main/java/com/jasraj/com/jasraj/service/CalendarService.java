package com.jasraj.com.jasraj.service;

import com.jasraj.dto.CalendarDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarService {

    public static Map<Integer,List<CalendarDto>> populateMonth(LocalDate referenceDate, int plusMinusMonths) {
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
            for (LocalDate loopDate = start; loopDate.isBefore(end); loopDate = loopDate.plusDays(1)) {
                calendarDtos.add(new CalendarDto().setDate(loopDate).setDayOfMonth(loopDate.getDayOfMonth()));
            }
        }
        return splitListMap(calendarDtos);
    }

    private static Map<Integer,List<CalendarDto>> splitListMap(List<CalendarDto> calendarDtos) {
        int splitSize = 7;

        Map<Integer,List<CalendarDto>> datesByRowNum = new HashMap<Integer, List<CalendarDto>>();
        for(int i = 0, rowNum =1 ; i < calendarDtos.size(); i = i + splitSize, rowNum++) {

            List<CalendarDto> row = calendarDtos.subList(i, i + splitSize > calendarDtos.size() ? calendarDtos.size() : i + splitSize);
            datesByRowNum.put(rowNum, row);
        }
        return datesByRowNum;

    }

    private static void addEmpty(List<CalendarDto> calendarDtos, int numOfDays) {
        for (int i = 0; i < numOfDays; i++) {
            calendarDtos.add(new CalendarDto().setDate(null).setDayOfMonth(0));
        }

    }


}
