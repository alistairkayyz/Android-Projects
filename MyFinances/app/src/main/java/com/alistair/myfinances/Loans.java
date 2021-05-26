package com.alistair.myfinances;

public class Loans {
    private int accountNumber;
    private double initBalance;
    private double currentBalance;
    private double interestRate;
    private double paymentAmount;

    public Loans (){
        this.accountNumber = 0;
        this.initBalance = 0.0;
        this.currentBalance = 0.0;
        this.interestRate = 0.0;
        this.paymentAmount = 0.0;
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

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
