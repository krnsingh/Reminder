package com.jasraj.entity;

/**
 * Created by Jaskirat on 29-04-2016.
 */
import javax.persistence.*;

/**
 * Created by Jaskirat on 29-04-2016.
 */
@Entity
@Table(name="username",
        uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}