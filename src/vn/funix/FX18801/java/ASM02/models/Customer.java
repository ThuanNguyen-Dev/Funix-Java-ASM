package vn.funix.FX18801.java.ASM02.models;

import java.text.DecimalFormat;
import java.util.*;

public class Customer extends User {
    private final List<Account> account;

    public Customer(String name, String customerId) {
        super(name, customerId);
        this.account = new ArrayList<>();
    }


    public List<Account> getAccount() {
        return account;
    }

    /**
     * Function to help check premium for each account
     * */
    public boolean isPremium() {
        for (int i = 0; i < this.account.size(); i++) {
            Account checkedisPremium = this.account.get(i);
            if (checkedisPremium.isPremium() == true) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function to help add accounts
     * @param accountNumber,balance
     * @return true
     * */
    public boolean addAccount(String accountNumber, double balance) {
        if (findAccount(accountNumber) == null) {
            this.account.add(new Account(accountNumber, balance));
            return true;
        }
        return false;
    }

    /**
     * Function to help get sum balance
     * @return 1,000,000đ
     * */
    public String getBalance() {
        double sumBalance = 0;
        for (int i = 0; i < this.account.size(); i++) {
            sumBalance += account.get(i).getBalance();
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(sumBalance)+"đ";
    }

    /**
     * Function to help print according to the form to the console
     * */
    public void displayInformation() {
        String CCCD = getCustomerId();
        String name = getName();
        String customerType = "Normal";
        if (isPremium() == true) {
            customerType = "Premium";
        }
        String sumBalance = getBalance();
        System.out.println(CCCD + " | \t\t\t\t\t| " + name + " | " + customerType + " | " + sumBalance);
        for (int i = 0; i < this.account.size(); i++) {
            System.out.println(i+1 + "\t" + account.get(i));
        }
    }

    /**
     * Function to help find account
     * @param accountNumber
     * @return Account
     * */
    private Account findAccount(String accountNumber) {
        for (int i = 0; i < this.account.size(); i++) {
            Account checkedAccount = this.account.get(i);
            if (checkedAccount.getAccountNumber().equals(accountNumber)) {
                return checkedAccount;
            }
        }
        return null;
    }
}
