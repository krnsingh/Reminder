package com.jasraj.service;

import com.jasraj.config.HibernateConfig;
import com.jasraj.dto.AlertDto;
import com.jasraj.entity.Alert;
import com.jasraj.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlertService {

    private static SessionFactory sessionFactory = HibernateConfig.getSession();

    public List<AlertDto> getAlerts(String email, int day, int month, int year) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where emailId = :email")
                .setParameter("email", email);
        User user = (User) query.uniqueResult();
        List<Alert> alerts = user.getAlerts();

        List<Alert> filteredAlerts = alerts.stream().filter(alert -> DateService.compare(alert.getLocalDate(), LocalDateTime.of(year, month, day, 0 ,0))).collect(Collectors.toList());
        session.getTransaction().commit();
        return convert(filteredAlerts);
    }

    public List<Alert> getAlerts(String email) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where emailId = :email")
                .setParameter("email", email);
        User user = (User) query.uniqueResult();
        if(user == null) {
            return new ArrayList<>();
        }
        List<Alert> alerts = user.getAlerts();
        session.getTransaction().commit();
        return alerts;
    }

    public boolean deleteAlert(int id) {
        boolean retVal = false;
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Alert alert = (Alert)session.load(Alert.class, id);
        if(alert != null) {
            session.delete(alert);
            retVal = true;
        }
        session.getTransaction().commit();
        return retVal;
    }

    public void addUserAlerts(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User existingUser = fetchUserIfExists(user, session);
        if (existingUser != null) {
            Alert alert = user.getAlerts().get(0);
            alert.setUser(existingUser);
            session.save(alert);
            session.save(existingUser);
        } else {
            session.save(user);
        }
        session.getTransaction().commit();
    }

    private User fetchUserIfExists(User user, Session session) {
        Query query = session.createQuery("from User where emailId = :email")
                .setParameter("email", user.getEmailId());
        return (User) query.uniqueResult();
    }

    private List<AlertDto> convert(List<Alert> alerts) {
        List<AlertDto> alertDtos = new ArrayList<>();
        alerts.forEach((alert) -> {
            alertDtos.add(new AlertDto().setEmail(alert.getUser().getEmailId())
                    .setMsg(alert.getMessage())
                    .setYear(alert.getLocalDate().getYear())
                    .setMonth(alert.getLocalDate().getMonth().getValue())
                    .setDay(alert.getLocalDate().getDayOfMonth())
                    .setHh(alert.getLocalDate().getHour())
                    .setMm(alert.getLocalDate().getMinute())
                    .setId(alert.getId()));
        });
        return alertDtos;
    }

}
