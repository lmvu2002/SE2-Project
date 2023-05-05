package com.project.se2project.domain.Saving;

import com.project.se2project.model.Saving;
import com.project.se2project.model.User;

import java.io.Serializable;

public class GetSavingResponse implements Serializable {

    private long id;

    private long userid;

    private String username;
    private long money;
    private String startDate;
    private long rate;
    private String nextIncomeDate;

    private String message;

    public GetSavingResponse() {

    }

    public GetSavingResponse(Saving saving) {
        this.id = saving.getId();
        this.userid = saving.getUser().getId();
        this.username = saving.getUser().getUsername();
        this.money = saving.getMoney();
        this.startDate = saving.getStartDate();
        this.rate = saving.getRate();
        this.nextIncomeDate = saving.getNextIncomeDate();
    }

    public GetSavingResponse(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username){
            this.username = username;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
