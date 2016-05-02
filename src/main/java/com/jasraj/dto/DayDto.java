package com.jasraj.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class DayDto implements Serializable {

    private LocalDateTime localDate;
    private boolean alertExists = false;

    public boolean isAlertExists() {
        return alertExists;
    }

    public DayDto setAlertExists(boolean alertExists) {
        this.alertExists = alertExists;
        return this;
    }

    public LocalDateTime getLocalDate() {
        return localDate;
    }

    public DayDto setLocalDate(LocalDateTime localDate) {
        this.localDate = localDate;
        return this;
    }
}
