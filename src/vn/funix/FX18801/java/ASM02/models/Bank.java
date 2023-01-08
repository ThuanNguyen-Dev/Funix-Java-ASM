package vn.funix.FX18801.java.ASM02.models;

import java.util.*;

public class Bank {

    private final String id;
    private final List<Customer> customer;

    public Bank() {
        this.id = String.valueOf(UUID.randomUUID());
        this.customer = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    /**
     * Function to help add customer
     * @param newCustomer
     * @return true
     * */
    public boolean addCustomer(Customer newCustomer) {
        String customerId = newCustomer.getCustomerId();
        if (isCustomerExisted(customerId) != true && customerId != null) {
            this.customer.add(newCustomer);
            return true;
        }
        return false;
    }

    /**
     * Function to help add accounts with customer
     * @param customerId,account
     * @return true
     * */
    public boolean addAccount(String customerId, Account account) {
        if (isCustomerExisted(customerId)) {
            for (int i = 0; i < this.customer.size(); i++) {
                Customer checkedCustomer = this.customer.get(i);
                checkedCustomer.getCustomerId();
                if (checkedCustomer.getCustomerId().equals(customerId)) {
                    checkedCustomer.addAccount(account.getAccountNumber(), account.getBalance());
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Customer check function already exists
     * @param customerId
     * @return true
     * */
    public boolean isCustomerExisted(String customerId) {
        for (int i = 0; i < this.customer.size(); i++) {
            Customer checkedCustomer = this.customer.get(i);
            if (checkedCustomer.getCustomerId().equals(customerId)) {
                return true;
            }
        }
        return false;
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    /**
     * Function to help find account with customerId
     * @param customerId,accountNumber
     * @return true
     * */
    public boolean findAccount(String customerId, String accountNumber) {
        boolean b = false;
        for (int i = 0; i < this.customer.size(); i++) {
            Customer checkedCustomer = this.customer.get(i);
            if (checkedCustomer.getCustomerId().equals(customerId)) {
                for (int j = 0; j < checkedCustomer.getAccount().size(); j++) {
                    Account checkedAccount = checkedCustomer.getAccount().get(j);
                    if (checkedAccount.getAccountNumber().equals(accountNumber)) {
                        b = true;
                        break;
                    }
                }
            }
        }
        return b;
    }

}
