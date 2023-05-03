package com.project.se2project.domain.Deposit;

import com.project.se2project.model.Deposit;
import com.project.se2project.model.User;

import java.io.Serializable;

public class GetDepositResponse implements Serializable {

    private long id;

    private User user;

    private long money;

    private String startDate;

    private long rate;

    private int duration;

    private String endDate;

    private long totalMoney;

    public GetDepositResponse() {
    }

    public GetDepositResponse(Deposit deposit) {
        this.id = deposit.getId();
        this.user = deposit.getUser();
        this.money = deposit.getMoney();
        this.startDate = deposit.getStartDate();
        this.rate = deposit.getRate();
        this.duration = deposit.getDuration();
        this.endDate = deposit.getEndDate();
        this.totalMoney = deposit.getTotalMoney();
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

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(long totalMoney) {
        this.totalMoney = totalMoney;
    }
}
