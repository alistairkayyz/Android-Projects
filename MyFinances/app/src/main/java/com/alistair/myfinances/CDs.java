package com.alistair.myfinances;

public class CDs {
    private int accountNumber;
    private double initBalance;
    private double currentBalance;
    private double interestRate;

    public CDs(){
        this.accountNumber = 0;
        this.initBalance = 0.0;
        this.currentBalance = 0.0;
        this.interestRate = 0.0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getInitBalance() {
        return initBalance;
    }

    public void setInitBalance(double initBalance) {
        this.initBalance = initBalance;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
