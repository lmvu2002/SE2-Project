package com.project.se2project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Entity;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
public class Loan {

    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @PrePersist
    public void generateId() {
        Random random = new Random();
        this.id = random.nextLong(90000) + 10000;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private long inMoney;

    private String startDate;

    private int duration;

    private String endDate;

    private long rate;

    private long totalMoney;

    private long paidMoney;

    private String nextPaymentDate;

    private long nextPayment;

    private boolean isPaid;

    private long remainMoney;

    public Loan(User user, long inMoney, String startDate, int duration, long rate) {
        generateId();
        this.user = user;
        this.inMoney = inMoney;
        this.startDate = startDate;
        this.duration = duration;
        setEndDate(startDate);
        this.rate = rate;
        setTotalMoney(inMoney, rate, duration);
        setNextPaymentDate(startDate);
        this.paidMoney = 0;
        setNextPayment(inMoney, rate, duration);
        this.isPaid = false;
        this.remainMoney = totalMoney;
    }

    public Loan() {

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

    public long getInMoney() {
        return inMoney;
    }

    public void setInMoney(long inMoney) {
        this.inMoney = inMoney;
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

    public long getId() {
        return id;
    }

    public long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(long inMoney, long rate, int duration) {
        double interest = rate / 100.0;
        //the money have to pay every month
        double principalPayPerMonth = inMoney / duration;
        //the first month interest
        double firstInterest = (inMoney * interest) / duration;
        //from 2nd month
        double totalInterest = firstInterest;
        double currentLoan = inMoney - principalPayPerMonth;
        for (int i = 1; i < duration; i++) {
            totalInterest += (currentLoan * interest) / duration;
            currentLoan -= principalPayPerMonth;
        }
        // return total payment amount
        double totalPayment = inMoney + totalInterest;
//        double monthlyPayment = (inMoney * interest) / (1 - Math.pow(1 + interest, -duration));
//        double balance = inMoney;
//        double totalPayment = 0.0;
//        for (int i = 1; i <= duration; i++) {
//            double allInterest = balance * interest;
//            double principal = monthlyPayment - allInterest;
//            balance -= principal;
//            totalPayment += monthlyPayment;
//        }
        this.totalMoney = Math.round(totalPayment);
    }
    public void setTotalMoney(long totalMoney) {
        this.totalMoney = totalMoney;
    }
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String startDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate formattedStartDate = LocalDate.parse(startDate, dtf);
        LocalDate end = formattedStartDate.plusMonths(duration);
        this.endDate = end.format(dtf);
    }

    public String getNextPaymentDate() {
        return nextPaymentDate;
    }

    public void setNextPaymentDate(String startDate) {
        if(startDate != this.startDate) {
            this.nextPaymentDate = startDate;
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate formattedStartDate = LocalDate.parse(startDate, dtf);
        LocalDate next = formattedStartDate.plusMonths(1);
        this.nextPaymentDate = next.format(dtf);
    }

    public long getNextPayment() {
        return nextPayment;
    }

    public void setNextPayment(long money, long rate, int duration) {

        double nextPayment = 0;

        double interest = rate / 100.0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate formattedStartDate = LocalDate.parse(startDate, dtf);
        LocalDate next = formattedStartDate.plusMonths(1);
        double principalPayPerMonth = inMoney / duration;
        if (this.nextPaymentDate == next.format(dtf)) {
            nextPayment = principalPayPerMonth + (inMoney * interest) / duration;
        } else {
            nextPayment = principalPayPerMonth + (money * interest) / duration;
        }
        // Cast the result to a long
        this.nextPayment = (long) nextPayment;

//        double interest = rate / 100.0;
//        //the money have to pay every month
//        double principalPayPerMonth = inMoney / duration;
//        //the first month interest
//        double firstInterest = (inMoney * interest) / duration;
//        //from 2nd month
//        double totalInterest = firstInterest;
//        double currentLoan = inMoney - principalPayPerMonth;
//        for (int i = 1; i < duration; i++) {
//            totalInterest += (currentLoan * interest) / duration;
//            currentLoan -= principalPayPerMonth;
    }

    public void setNextPayment(long nextPayment) {
        this.nextPayment = nextPayment;
    }

    public long getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(long paidMoney) {
        this.paidMoney = paidMoney;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public long getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(long remainMoney) {
        this.remainMoney = remainMoney;
    }
}
