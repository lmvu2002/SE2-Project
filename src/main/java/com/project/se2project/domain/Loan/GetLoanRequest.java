package com.project.se2project.domain.Loan;

import com.project.se2project.model.Loan;

import java.io.Serializable;

public class GetLoanRequest implements Serializable {
    private Long id;
    private Long inMoney;
    private String startDate;
    private int duration;
    private Long rate;
    private Long totalMoney;

    public GetLoanRequest(Loan loan) {
        this.id = loan.getId();
        this.inMoney = loan.getInMoney();
        this.startDate = loan.getStartDate();
        this.duration = loan.getDuration();
        this.rate = loan.getRate();
        this.totalMoney = loan.getTotalMoney();
    }

    public GetLoanRequest() {

    }

    public Long getId() {
        return id;
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
