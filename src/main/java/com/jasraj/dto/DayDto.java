package com.jasraj.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class DayDto implements Serializable {

    private LocalDate localDate;
    private boolean alertExists = true;

    public boolean isAlertExists() {
        return alertExists;
    }

    public void setAlertExists(boolean alertExists) {
        this.alertExists = alertExists;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public DayDto setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
        return this;
    }
}
