package com.project.se2project.domain.User;

import com.project.se2project.model.Loan;
import com.project.se2project.model.User;


import java.io.Serializable;

public class LoanRequest implements Serializable {

    private Long id;

    private User user;

    private Long inMoney;

    private String startDate;

    private int duration;

    private Long rate;

    private Long totalMoney;

    public LoanRequest(Loan loan) {
        this.user = loan.getUser();
        this.inMoney = loan.getInMoney();
        this.startDate = loan.getStartDate();
        this.duration = loan.getDuration();
        this.rate = loan.getRate();
        this.totalMoney = loan.getTotalMoney();
    }

    public LoanRequest() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getInMoney() {
        return inMoney;
    }

    public void setInMoney(Long inMoney) {
        this.inMoney = inMoney;
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

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;}

}
