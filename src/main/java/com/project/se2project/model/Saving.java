package com.project.se2project.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Entity
public class Saving {
    private static Set<Long> generatedIds = new HashSet<>();

    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @PrePersist
    public void generateId() {
        Random random = new Random();
        long newId;
        do {
            newId = random.nextLong(90000) + 10000;
        } while (generatedIds.contains(newId));
        generatedIds.add(newId);
        this.id = newId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private long money;

    private String startDate;

    private long rate;

    private String nextIncomeDate;

    public Saving(User user, long money, String startDate, long rate) {
        generateId();
        this.user = user;
        this.money = money;
        this.startDate = startDate;
        this.rate = rate;
        setNextIncomeDate(startDate);
    }

    public Saving() {
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

    public Long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

    public String getNextIncomeDate() {
        return nextIncomeDate;
    }

    public void setNextIncomeDate(String nextIncomeDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(nextIncomeDate, formatter);
        date = date.plusDays(1);
        this.nextIncomeDate = date.format(formatter);
    }
}
