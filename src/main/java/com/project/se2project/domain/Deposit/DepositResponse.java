package com.project.se2project.domain.Deposit;

import com.project.se2project.model.User;

public class DepositResponse {
    private long id;

    private User user;

    private long money;

    private String startDate;

    private long rate;

    private int duration;

    private String endDate;

    private long totalMoney;

    private String message;

    public DepositResponse() {
    }

    public DepositResponse(long id, User user, long money, String startDate, long rate, int duration, String endDate, long totalMoney) {
        this.id = id;
        this.user = user;
        this.money = money;
        this.startDate = startDate;
        this.rate = rate;
        this.duration = duration;
        this.endDate = endDate;
        this.totalMoney = totalMoney;
    }

    public DepositResponse(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public long getMoney() {
        return money;
    }

    public String getStartDate() {
        return startDate;
    }

    public long getRate() {
        return rate;
    }

    public int getDuration() {
        return duration;
    }

    public String getEndDate() {
        return endDate;
    }

    public long getTotalMoney() {
        return totalMoney;
    }
}
