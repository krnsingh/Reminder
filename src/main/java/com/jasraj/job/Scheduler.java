package com.jasraj.job;

import com.jasraj.service.EmailService;

import java.util.Timer;

public class Scheduler {

    public static void start() {
        Timer timer = new Timer();
        EmailService emailService = new EmailService();
        timer.schedule(emailService, 0, 900000);  //Fetch all alerts after 15 mins
    }

}
