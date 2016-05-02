package com.jasraj.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime localDate;
    private String message;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getLocalDate() {
        return localDate;
    }

    public Alert setLocalDate(LocalDateTime localDate) {
        this.localDate = localDate;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Alert setUser(User user) {
        this.user = user;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Alert setMessage(String message) {
        this.message = message;
        return this;
    }
}
