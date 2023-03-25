package vn.funix.FX18801.java.ASM03;

import vn.funix.FX18801.java.ASM03.models.Account;
import vn.funix.FX18801.java.ASM03.models.Customer;
import vn.funix.FX18801.java.ASM03.models.DigitalBank;

import java.util.Scanner;

public class ASM03 {
    public static final String AUTHOR = "FX18801";
    public static final String VERSION = "v3.0.0";
    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static final Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "001215000001";
    private static final String CUSTOMER_NAME = "FUNIX";

    public static void main(String[] args) {
        addCustomer(CUSTOMER_ID, CUSTOMER_NAME);
        init();
    }

    private static void init() {
        menu(0);
        int chooseMenu = checkInt(0, 5, 0, 0);

        switch (chooseMenu) {
            case 0 -> outProgram();
            case 1 -> showCustomer();
            case 2 -> addAccountATM();
            case 3 -> addAccountLoan();
            case 4 -> System.out.println("Rút tiền");
            case 5 -> System.out.println("Lịch sử giao dịch");
        }
    }

    public static void menu(int menuNumber) {
        if (menuNumber == 0) {
            System.out.println("+----------+------------------------+----------+");
            System.out.println("| NGAN HANG SO | " + AUTHOR + "@" + VERSION + "                |");
            System.out.println("+----------+------------------------+----------+");
            System.out.println("  1. Thông tin khách hàng                       ");
            System.out.println("  2. Thêm tài khoản ATM                         ");
            System.out.println("  3. Thêm tài khoản tín dụng                    ");
            System.out.println("  4. Rút tiền                                   ");
            System.out.println("  5. Lịch sử giao dịch                          ");
            System.out.println("  0. Thoát                                      ");
            System.out.println("+----------+------------------------+----------+");
            System.out.println("Chức năng: ");
        } else if (menuNumber == 1) {
            System.out.println("\nMời lựa chọn chức năng: ");
            System.out.println("1. Tiếp tục.");
            System.out.println("2. Quay lại MENU chính.");
            System.out.println("0. Thoát Chương trình.");
            System.out.println("+-------------------------------------------------+");
            System.out.println("Chức năng: ");
        }

    }

    /**
     * Function to help check whether the number is an integer and within the allowed range
     *
     * @return min <= chooseMenu <= max
     */
    public static Integer checkInt(int min, int max, int notInt, int menuNumber) {
        int chooseMenu = 0;
        Scanner scanner = new Scanner(System.in);
        while (notInt == 0) {
            String result = scanner.nextLine();
            if ((result).matches("-?\\d+") && (Integer.parseInt(result) >= min && Integer.parseInt(result) <= max)) {
                chooseMenu = Integer.parseInt(result);
                System.out.println("+-------------------------------------------------+");
                notInt = 1;
                break;
            } else if (result.equals("menu")) {
                menu(menuNumber);
            } else {
                if (menuNumber == 1) {
                    System.out.println("Vui lòng nhập lại lựa chọn!");
                } else {
                    System.out.println("Vui lòng nhập lại lựa chọn!\t - \t(Xem lại menu nhập \"menu\" từ bàn phím)");
                }
            }
        }
        return chooseMenu;
    }

    public static boolean checkMaSTK(String CCCD, String maSTK) {
        boolean b = false;
        try {
            if (maSTK.length() == 6 && maSTK.matches("[0-9]+")) {
                if (activeBank.findAccount(CCCD, maSTK)) {
                    System.out.println("Tài khoản " + maSTK + " của khách hàng " + CCCD + " đã tồn tại!");
                    System.out.println("Vui lòng nhập STK khác:");
                } else {
                    b = true;
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Mã STK không hợp lệ.\n" +
                    "Vui lòng nhập lại hoặc nhập ‘No’ để thoát chương trình");
        }
        return b;
    }

    public static void addCustomer(String CCCD, String nameCustomer) {
        if (activeBank.isCustomerExisted(CCCD)) {
            System.out.println("Khách hàng " + CCCD + " đã tồn tại!");
        } else if (activeBank.addCustomer(new Customer(nameCustomer, CCCD))) {
            System.out.println("Đã thêm khách hàng " + CCCD + " vào danh sách");
        }
    }

    private static void showCustomer() {
        Customer customer = activeBank.getCustomerbyId(CUSTOMER_ID);
        if (customer != null)
            customer.displayInformation();
    }

    private static void addAccountATM() {
        String ma_ATM = "";

        System.out.println("Nhập mã số tài khoản gồm 6 chữ số: ");
        while (scanner.hasNext()) {
            ma_ATM = scanner.next();
            if (checkMaSTK(CUSTOMER_ID, ma_ATM)) {
                break;
            }
        }

        System.out.println("Nhập số dư: ");
        while (scanner.hasNext()) {
            try {
                double balance = scanner.nextDouble();
                if (balance >= 50000) {
                    if (activeBank.addAccount(CUSTOMER_ID, new Account(ma_ATM, accountType(0), balance))) {
                        System.out.println("Đã lưu tài khoản " + ma_ATM + " vào khách hàng " + CUSTOMER_ID);
                    }
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Số dư tài khoản phải lớn hơn 50,000 VNĐ");
                System.out.println("Vui lòng nhập lại: ");
            }
        }

        showCustomer();
    }
    private static void addAccountLoan() {
        String maLOAN = "";

        System.out.println("Nhập mã số tài khoản gồm 6 chữ số: ");
        while (scanner.hasNext()) {
            maLOAN = scanner.next();
            if (checkMaSTK(CUSTOMER_ID, maLOAN)) {
                break;
            }
        }

        System.out.println("Nhập số dư: ");
        while (scanner.hasNext()) {
            try {
                double balance = scanner.nextDouble();
                if (balance >= 50000) {
                    if (activeBank.addAccount(CUSTOMER_ID, new Account(maLOAN, accountType(1), balance))) {
                        System.out.println("Đã lưu tài khoản " + maLOAN + " vào khách hàng " + CUSTOMER_ID);
                    }
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Số dư tài khoản phải lớn hơn 50,000 VNĐ");
                System.out.println("Vui lòng nhập lại: ");
            }
        }

        showCustomer();
    }


    public static void outProgram() {
        System.out.println("Đã thoát chương trình!!");
        System.exit(0);
    }

    public static String accountType(int selectType) {
        String accountType = "";
        if (selectType == 0) {
            accountType = "SAVINGS";
        } else if (selectType == 1) {
            accountType = "LOAN";
        }
        return accountType;
    }
}
