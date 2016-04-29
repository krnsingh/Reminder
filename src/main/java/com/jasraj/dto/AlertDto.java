package com.jasraj.dto;

import java.io.Serializable;

public class AlertDto implements Serializable {

    private String email;
    private String msg;
    private int month;
    private int year;
    private int hh;
    private int mm;

    public String getEmail() {
        return email;
    }

    public AlertDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public AlertDto setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public AlertDto setMonth(int month) {
        this.month = month;
        return this;
    }

    public int getYear() {
        return year;
    }

    public AlertDto setYear(int year) {
        this.year = year;
        return this;
    }

    public int getHh() {
        return hh;
    }

    public AlertDto setHh(int hh) {
        this.hh = hh;
        return this;
    }

    public int getMm() {
        return mm;
    }

    public AlertDto setMm(int mm) {
        this.mm = mm;
        return this;
    }
}
