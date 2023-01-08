package vn.funix.FX18801.java.ASM02.models;

import java.text.DecimalFormat;

public class Account {

    private String accountNumber;
    private double balance = 0;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Function to help check premium
     * */
    public  boolean isPremium() {
        if (balance >= 10000000){
            return true;
        }
        return false;
    }

    /**
     * Function to help print according to the form to the console
     * */
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return accountNumber + "   | \t\t\t\t\t\t\t\t\t    " + formatter.format(balance)+"đ";
    }
}
