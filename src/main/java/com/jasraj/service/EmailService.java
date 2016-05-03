package com.jasraj.service;

import com.jasraj.dto.EmailDto;
import com.jasraj.entity.Alert;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailService extends TimerTask {

    private static Properties mailServerProperties = System.getProperties();
    private static Session getMailSession;
    private static MimeMessage generateMailMessage;
    private AlertService alertService = new AlertService();



    private void sendEmails(List<EmailDto> emailDtos) throws MessagingException {
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);

        generateMailMessage.setSubject("Reminder email");
        Transport transport = getMailSession.getTransport("smtp");
        try {
            for (EmailDto emailDto : emailDtos) {
                String emailBody = emailDto.getMessage();
                generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDto.getEmail()));
                generateMailMessage.setContent(emailBody, "text/html");
                if(!transport.isConnected())
                    transport.connect("smtp.gmail.com", "TODO", "TODO");
                transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            }
        } finally {
            transport.close();
        }

    }

    @Override
    public void run() {
        try {
            List<EmailDto> emailDtos = alertService.getAlertsForEmail();
            if(emailDtos.size() > 0)
                sendEmails(emailDtos);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
