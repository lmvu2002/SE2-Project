package com.project.se2project.domain.Saving;

import com.project.se2project.model.Saving;
import com.project.se2project.model.User;

import java.io.Serializable;

public class SavingRequest implements Serializable {
    private long id;

    private User user;

    private long money;

    private String startDate;

    private long rate;

    private String nextIncomeDate;

    public SavingRequest(Saving saving) {
        this.id = saving.getId();
        this.user = saving.getUser();
        this.money = saving.getMoney();
        this.startDate = saving.getStartDate();
        this.rate = saving.getRate();
        this.nextIncomeDate = saving.getNextIncomeDate();
    }

    public SavingRequest() {
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

    public String getNextIncomeDate() {
        return nextIncomeDate;
    }

    public void setNextIncomeDate(String nextIncomeDate) {
        this.nextIncomeDate = nextIncomeDate;
    }
}
