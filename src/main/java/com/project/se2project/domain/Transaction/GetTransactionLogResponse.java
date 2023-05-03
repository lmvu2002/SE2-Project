package com.project.se2project.domain.Transaction;

import com.project.se2project.model.Loan;
import com.project.se2project.model.Saving;
import com.project.se2project.model.Transaction;
import com.project.se2project.model.User;

import java.io.Serializable;

public class GetTransactionLogResponse implements Serializable {
    //TRANSFER
    private Long transactionId;

    private Long fromUserId;

    private Long toUserId;

    private int amount;

    private String transactionTime;
    //LOAN
    private Long loanId;
    private long userId;
    private String username;
    private Long inMoney;
    private String startDate;
    private int duration;
    private Long rate;
    private Long totalMoney;

    private long savingId;

    private User user;
    private long money;
    private String nextIncomeDate;

    private String type;

    private String message;
    public GetTransactionLogResponse(Transaction transaction) {
        this.type="TRANSFER";
        this.transactionId = transaction.getTransactionId();
        this.fromUserId = transaction.getFromUserId();
        this.toUserId = transaction.getToUserId();
        this.amount = transaction.getAmount();
        this.transactionTime = transaction.getTransactionTime();
    }

    public GetTransactionLogResponse(Loan loan) {
        this.type = "LOAN";
        this.loanId = loan.getId();
        this.userId = loan.getUser().getId();
        this.username = loan.getUser().getUsername();
        this.inMoney = loan.getInMoney();
        this.startDate = loan.getStartDate();
        this.duration = loan.getDuration();
        this.rate = loan.getRate();
        this.totalMoney = loan.getTotalMoney();

    }

    public GetTransactionLogResponse(Saving saving) {
        this.type = "SAVING";
        this.savingId = saving.getId();
        this.user = saving.getUser();
        this.money = saving.getMoney();
        this.startDate = saving.getStartDate();
        this.rate = saving.getRate();
        this.nextIncomeDate = saving.getNextIncomeDate();
    }

    public String getDate() {
        if (this.type.equals("TRANSFER")) {
            return this.transactionTime;
        }
        return this.startDate;
    }

    public GetTransactionLogResponse(String message) {
        this.message = message;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public int getAmount() {
        return amount;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public Long getLoanId() {
        return loanId;
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

    public long getSavingId() {
        return savingId;
    }

    public User getUser() {
        return user;
    }

    public long getMoney() {
        return money;
    }

    public String getNextIncomeDate() {
        return nextIncomeDate;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
