package com.project.se2project.domain.Saving;

public class SavingResponse {
    private long id;

    private long money;

    private String startDate;

    private long rate;

    private String nextIncomeDate;

    private String message;

    public SavingResponse(long id, long money, String startDate, long rate, String nextIncomeDate) {
        this.id = id;
        this.money = money;
        this.startDate = startDate;
        this.rate = rate;
        this.nextIncomeDate = nextIncomeDate;
    }

    public SavingResponse(String message) {
        this.message = message;
    }
    public SavingResponse() {
    }

    public long getId() {
        return id;
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

    public String getNextIncomeDate() {
        return nextIncomeDate;
    }
}
