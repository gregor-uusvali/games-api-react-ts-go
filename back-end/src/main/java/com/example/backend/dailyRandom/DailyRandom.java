package com.example.backend.dailyRandom;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "dailyRandom")
public class DailyRandom {
    @Id
    @GeneratedValue()
    int id;
    int number;
    Date today;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    @Override
    public String toString() {
        return "DailyRandom{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", today='" + today + '\'' +
                '}';
    }
}
