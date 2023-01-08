package vn.funix.FX18801.java.ASM02;

import vn.funix.FX18801.java.ASM02.models.Account;
import vn.funix.FX18801.java.ASM02.models.Bank;
import vn.funix.FX18801.java.ASM02.models.Customer;

import java.util.List;
import java.util.Scanner;

import static vn.funix.FX18801.java.ASM01.ASM01.checkCCCD;

public class ASM02 {

    /**
     * @author FX18801
     * @version v2.0.0
     */
    public static final String AUTHOR = "FX18801";
    public static final String VERSION = "v2.0.0";
    private static final Bank bank = new Bank();

    public static void main(String[] args) {
        // Tạo khách hàng và tài khoản mẫu
        bank.addCustomer(new Customer("Thuan", "046201009999"));
        bank.addCustomer(new Customer("Fu", "046201001001"));
        bank.addCustomer(new Customer("FUNIX", "001215000001"));

        bank.addAccount("046201009999", new Account("013021", 500000));
        bank.addAccount("046201009999", new Account("137201", 50000));
        bank.addAccount("046201001001", new Account("123456", 100000));
        bank.addAccount("046201001001", new Account("456789", 180000));
        bank.addAccount("001215000001", new Account("159753", 1000000));
        bank.addAccount("001215000001", new Account("189753", 10000000));
        bank.addAccount("001215000001", new Account("478965", 100000));

        // bắt đầu chương trình
        init();
    }

    public static void init() {
        menu(0);
        int chooseMenu = checkInt(0, 5, 0, 0);

        switch (chooseMenu) {
            case 0:
                outProgram();
            case 1:
                addCustomer();
                break;
            case 2:
                addAccountCustomer();
                break;
            case 3:
                listCustomer();
                break;
            case 4:
                findCustomerWithCCCD();
            case 5:
                findCustomerWithName();

        }
    }

    /**
     * Function to help check whether the number is an integer and within the allowed range
     *
     * @param min,max,notInt
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

    /**
     * The function help to print menu to console
     *
     * @param menuNumber display menu selection
     */
    public static void menu(int menuNumber) {
        if (menuNumber == 0) {
            System.out.println("+----------+------------------------+----------+");
            System.out.println("| NGAN HANG SO | " + AUTHOR + "@" + VERSION + "                |");
            System.out.println("+----------+------------------------+----------+");
            System.out.println("  1. Thêm khách hàng                            ");
            System.out.println("  2. Thêm tài khoản cho khách hàng              ");
            System.out.println("  3. Hiển thị danh sách khách hàng              ");
            System.out.println("  4. Tìm theo CCCD                              ");
            System.out.println("  5. Tìm theo tên khách hàng                    ");
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
     * Function to help save customers
     */
    public static void addCustomer() {
        String CCCD = "";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên khách hàng: ");
        String nameCustomer = scanner.next();
        System.out.print("Nhập số CCCD: ");

        while (scanner.hasNext()) {
            CCCD = scanner.next();
            if (bank.isCustomerExisted(CCCD)) {
                System.out.println("Khách hàng " + CCCD + " đã tồn tại!");
                break;
            } else if (bank.addCustomer(new Customer(nameCustomer, CCCD)) == true) {
                System.out.println("Đã thêm khách hàng " + CCCD + " vào danh sách");
                break;
            }
        }
        returnMenuOrOut(1);
    }

    /**
     * Function to help save accounts for customers
     */
    public static void addAccountCustomer() {
        Scanner scanner = new Scanner(System.in);
        String CCCD = "";
        String maSTK = "";
        int status = 0;

        System.out.println("Nhap CCCD khach hang: ");
        while (scanner.hasNext()) {
            CCCD = scanner.next();
            if (CCCDcheck(CCCD, status)) {
                break;
            } else {
                status++;
            }
        }

        System.out.println("Nhap ma STK gom 6 chu so: ");
        while (scanner.hasNext()) {
            maSTK = scanner.next();
            if (checkMaSTK(CCCD, maSTK)) {
                break;
            }
        }

        System.out.println("Nhap so du");
        while (scanner.hasNext()) {
            try {
                Double balance = scanner.nextDouble();
                if (balance >= 50000) {
                    if (bank.addAccount(CCCD, new Account(maSTK, balance))) {
                        System.out.println("Đã lưu tài khoản " + maSTK + " vào khách hàng " + CCCD);
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
        returnMenuOrOut(2);
    }

    /**
     * Function to help display customer list
     */
    public static void listCustomer() {
        System.out.println("Danh sách khách hàng: ");
        for (int i = 0; i < bank.getCustomer().size(); i++) {
            bank.getCustomer().get(i).displayInformation();
        }
        returnMenuOrOut(3);
    }

    /**
     * Function to help find customers by CCCD
     */
    public static void findCustomerWithCCCD() {
        String CCCD = "";
        int status = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số CCCD cần tìm: ");
        while (scanner.hasNext()) {
            CCCD = scanner.next();
            if (CCCDcheck(CCCD, status)) {
                break;
            } else {
                status++;
            }
        }
        if (bank.isCustomerExisted(CCCD) == true) {
            for (int i = 0; i < bank.getCustomer().size(); i++) {
                if (bank.getCustomer().get(i).getCustomerId().equals(CCCD)) {
                    System.out.println("Đã tìm thấy khách hàng có CCCD \"" + CCCD + "\" :");
                    bank.getCustomer().get(i).displayInformation();
                    break;
                }
            }
        } else {
            System.out.println("Khách hàng " + CCCD + " Không tìm thấy!");
        }
        returnMenuOrOut(4);
    }

    /**
     * Function to help find customers by name
     */
    private static void findCustomerWithName() {
        String name = "";
        boolean status = false;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập tên người cần tìm: ");
        name = scanner.nextLine().toLowerCase();
        List<Customer> customer = bank.getCustomer();
        for (int i = 0; i < customer.size(); i++) {
            String customerName = customer.get(i).getName().trim().toLowerCase();
            if (customerName.contains(name)) {
                customer.get(i).displayInformation();
                status = true;
            }
        }
        if (status == false) {
            System.out.println("Không tìm thấy khách hàng có tên " + name + " !");
        }
        returnMenuOrOut(5);
    }

    /**
     * Function to help continue program or exit program
     *
     * @param chuongTrinh function selection
     */
    public static void returnMenuOrOut(int chuongTrinh) {
        menu(1);
        int chooseMenu = checkInt(0, 2, 0, 1);

        switch (chooseMenu) {
            case 0:
                System.out.println("Thoát chương trình!!");
                System.exit(0);
            case 1:
                if (chuongTrinh == 1) {
                    addCustomer();
                } else if (chuongTrinh == 2) {
                    addAccountCustomer();
                } else if (chuongTrinh == 3) {
                    listCustomer();
                } else if (chuongTrinh == 4) {
                    findCustomerWithCCCD();
                } else if (chuongTrinh == 5) {
                    findCustomerWithName();
                }
                break;
            case 2:
                init();
                break;
        }
    }

    /**
     * Function to help check CCCD
     *
     * @param CCCD,status
     * @return true
     */
    public static boolean CCCDcheck(String CCCD, int status) {
        boolean b;
        try {
            if (CCCD.length() == 12 && CCCD.matches("[0-9]+") && checkCCCD(CCCD) == true) {
                b = true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            b = false;
            if (status >= 1) {
                if (CCCD.equals("No") || CCCD.equals("no")) {
                    System.exit(0);
                }
            }
            System.out.println("Số CCCD không hợp lệ.\n" +
                    "Vui lòng nhập lại hoặc nhập ‘No’ để thoát chương trình");
        }
        return b;
    }

    /**
     * Function to help check STK
     *
     * @param CCCD,maSTK
     * @return true
     */
    public static boolean checkMaSTK(String CCCD, String maSTK) {
        boolean b = false;
        try {
            if (maSTK.length() == 6 && maSTK.matches("[0-9]+")) {
                if (bank.findAccount(CCCD, maSTK) == true) {
                    System.out.println("Tài khoản " + maSTK + " của khách hàng " + CCCD + " đã tồn tại!");
                    System.out.println("Vui lòng nhập STK khác:");
                } else {
                    b = true;
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            b = false;
            System.out.println("Mã STK không hợp lệ.\n" +
                    "Vui lòng nhập lại hoặc nhập ‘No’ để thoát chương trình");
        }
        return b;
    }

    public static void outProgram(){
        System.out.println("Đã thoát chương trình!!");
        System.exit(0);
    }
}
