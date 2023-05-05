package com.project.se2project.domain.Deposit;

import com.project.se2project.model.Deposit;
import com.project.se2project.model.User;

import java.io.Serializable;

public class GetDepositResponse implements Serializable {

    private long id;

    private long userId;

    private String userName;

    private long money;

    private String startDate;

    private long rate;

    private int duration;

    private String endDate;

    private long totalMoney;

    private String message;

    public GetDepositResponse() {
    }

    public GetDepositResponse(Deposit deposit) {
        this.id = deposit.getId();
        this.userId = deposit.getUser().getId();
        this.userName = deposit.getUser().getName();
        this.money = deposit.getMoney();
        this.startDate = deposit.getStartDate();
        this.rate = deposit.getRate();
        this.duration = deposit.getDuration();
        this.endDate = deposit.getEndDate();
        this.totalMoney = deposit.getTotalMoney();
    }

    public GetDepositResponse(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
