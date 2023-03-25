package vn.funix.FX18801.java.ASM03.models;

import java.text.DecimalFormat;
import java.util.Objects;

public class Account {

    private String accountNumber;

    private String accountType;
    private double balance = 0;


    public Account(String accountNumber, String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Function to help check premium
     */
    public boolean isPremium() {
        return balance >= 10000000;
    }

    /**
     * Function to help print according to the form to the console
     */
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        if (Objects.equals(accountType, "LOAN")){
            return accountNumber + " |    " + accountType + "  | \t\t\t\t\t\t\t    | " + formatter.format(balance) + "đ";
        }
        return accountNumber + " | " + accountType + "  | \t\t\t\t\t\t\t    | " + formatter.format(balance) + "đ";
    }
}
