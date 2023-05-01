package com.project.se2project.domain.Loan;

import com.project.se2project.model.Loan;
import com.project.se2project.model.User;

import java.io.Serializable;

public class GetLoanResponse implements Serializable {
    private Long id;
    private long userId;
    private String username;
    private Long inMoney;
    private String startDate;
    private int duration;
    private Long rate;
    private Long totalMoney;

    public GetLoanResponse() {

    }

    public GetLoanResponse(Loan loan) {
        this.id = loan.getId();
        this.userId = loan.getUser().getId();
        this.username = loan.getUser().getUsername();
        this.inMoney = loan.getInMoney();
        this.startDate = loan.getStartDate();
        this.duration = loan.getDuration();
        this.rate = loan.getRate();
        this.totalMoney = loan.getTotalMoney();
    }

    public Long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Long getInMoney() {
        return inMoney;
    }

    public String getStartDate() {
        return startDate;
    }

    public int getDuration() {
        return duration;
    }

    public Long getRate() {
        return rate;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }
}
