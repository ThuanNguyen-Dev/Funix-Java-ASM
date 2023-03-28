package vn.funix.FX18801.java.ASM03;

import vn.funix.FX18801.java.ASM03.models.Account;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils extends Account {

    public Utils(String accountNumber, String accountType, double balance) {
        super(accountNumber, accountType, balance);
    }

    public static String getDivider() {
        return "+----------+----------------------------+-----------";
    }

    public static String getDatetime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        return formatter.format(date);
    }

    public static String formatBalance(double amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(amount) + "đ";
    }
}
