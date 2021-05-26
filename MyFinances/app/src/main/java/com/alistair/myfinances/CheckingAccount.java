package com.alistair.myfinances;

public class CheckingAccount {
    private int accountNumber;
    private double currentBalance;

    public CheckingAccount(){
        this.accountNumber = 0;
        this.currentBalance = 0;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }
}
