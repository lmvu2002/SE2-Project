package com.project.se2project.domain.User;

import java.time.LocalDate;

public class LoanRequest {
    private Long user;
    private Long inMoney;

    private LocalDate startDate;

    private Long rate;

    private Long totalMoney;
    public LoanRequest() {
    }

    public LoanRequest(Long user, Long inMoney, LocalDate startDate, Long rate, Long totalMoney) {
        this.user = user;
        this.inMoney = inMoney;
        this.startDate = startDate;
        this.rate = rate;
        this.totalMoney = totalMoney;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getInMoney() {
        return inMoney;
    }

    public void setInMoney(Long inMoney) {
        this.inMoney = inMoney;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }
}
