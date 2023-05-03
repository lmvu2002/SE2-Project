package com.project.se2project.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Entity
public class Deposit {
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @PrePersist
    public void generateId() {
        Random random = new Random();
        this.id = random.nextLong(90000) + 10000;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private long money;

    private String startDate;

    private long rate;

    private int duration;

    private String endDate;

    private long totalMoney;

    public Deposit(User user, long money, String startDate, long rate, int duration, String endDate, long totalMoney) {
        generateId();
        this.user = user;
        this.money = money;
        this.startDate = startDate;
        this.rate = rate;
        this.duration = duration;
        this.endDate = endDate;
        this.totalMoney = totalMoney;
    }

    public Deposit() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String startDate, int duration) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate formattedStartDate = LocalDate.parse(startDate, dtf);
        LocalDate end = formattedStartDate.plusMonths(duration);
        this.endDate = end.format(dtf);
    }

    public long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(long money, long rate, int duration) {
        this.totalMoney = money + (money * rate * duration) / 100;
    }
}
