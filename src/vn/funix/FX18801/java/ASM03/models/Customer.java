package vn.funix.FX18801.java.ASM03.models;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
        for (Account checkedisPremium : this.account) {
            if (checkedisPremium.isPremium()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function to help add accounts
     */
    public void addAccount(String accountNumber, String accountType, double balance) {
        if (findAccount(accountNumber) == null) {
            this.account.add(new Account(accountNumber, accountType ,balance));
        }
    }

    /**
     * Function to help get sum balance
     * @return 1,000,000đ
     * */
    public String getBalance() {
        double sumBalance = 0;
        for (Account value : this.account) {
            sumBalance += value.getBalance();
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
        String customerType = "Normal ";
        if (isPremium()) {
            customerType = "Premium";
        }
        String sumBalance = getBalance();
        System.out.println(CCCD + " |   " +  name + "  | " + customerType + " | \t\t\t\t\t| " + sumBalance);
        for (int i = 0; i < this.account.size(); i++) {
            System.out.println(i+1 + "\t  " + account.get(i));
        }
    }

    /**
     * Function to help find account
     * @return Account
     * */
    private Account findAccount(String accountNumber) {
        for (Account checkedAccount : this.account) {
            if (checkedAccount.getAccountNumber().equals(accountNumber)) {
                return checkedAccount;
            }
        }
        return null;
    }
}
