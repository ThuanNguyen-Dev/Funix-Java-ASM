package vn.funix.FX18801.java.ASM01;

import java.util.*;

public class ASM01 {
    /*
     *   Variable global
     *   @param AUTHOR,VERSION
     * */
    public static final String AUTHOR = "FX18801";
    public static final String VERSION = "v1.0.0";

    public static void main(String[] args) {
        // start program
        init();
    }

    public static void init() {
        // variable local
        int notInt = 0;
        String CCCD = "";
        int chooseMenu = 0;
        int chooseSubMenu = 0;
        int chooseSecurityCode = 0;
        Boolean b = false;

        // show main menu
        menu(0);
        Scanner scanner = new Scanner(System.in);
        // check the menu option if the selection is in the menu will exit otherwise return to the menu
        while (notInt == 0) {
            String result = scanner.nextLine();
            if ((result).matches("-?\\d+") && (Integer.parseInt(result) >= 0 && Integer.parseInt(result) <= 1)) {
                chooseMenu = Integer.parseInt(result);
                notInt = 1;
                break;
            } else if (result.equals("menu")) {
                menu(0);
            } else {
                System.out.println("Vui long nhap lai lua chon!\t - \t(Xem lai menu nhap \"menu\")");
            }
        }

        switch (chooseMenu) {
            case 0:
                System.out.println("Thoat chuong trinh!!");
                System.exit(0);
            case 1:
                notInt = 0;
                menu(2);
                while (notInt == 0) {
                    String result = scanner.next();
                    if ((result).matches("-?\\d+") && (chooseSecurityCode >= 0 && chooseSecurityCode <= 2)) {
                        chooseSecurityCode = Integer.parseInt(result);
                        notInt = 1;
                        break;
                    } else {
                        System.out.println("Vui long nhap lai lua chon!");
                    }
                }
                switch (chooseSecurityCode) {
                    case 0:
                        System.out.println("Thoat chuong trinh!!");
                        System.exit(0);
                    case 1:
                        int randomNumber = randomNumber();
                        System.out.println("Nhap ma xac thuc: " + randomNumber);

                        // Entering the wrong code will re-enter the code, the correct code will exit the loop
                        while (scanner.hasNext()) {
                            String code = scanner.next();
                            if ((code).matches("-?\\d+") && Integer.valueOf(code) == randomNumber) {
                                b = true;
                                break;
                            } else {
                                System.out.println("Ma xac thuc khong dung. Vui long nhap lai!");
                            }
                        }
                        break;
                    case 2:
                        String ranAlphaNumeric = randomNumberString(6);
                        System.out.println("Nhap ma xac thuc: " + ranAlphaNumeric);

                        // Entering the wrong code will re-enter the code, the correct code will exit the loop
                        while (scanner.hasNext()) {
                            String code = scanner.next();
                            // use compareTo to compare strings if the same is greater than 0 otherwise less than 0
                            if (code.compareTo(ranAlphaNumeric) < 0) {
                                System.out.println("Ma xac thuc khong dung. Vui long nhap lai!");
                            } else {
                                b = true;
                                break;
                            }
                        }
                        break;
                }

                if (b == true) {
                    // set b = false
                    b = false;
                    System.out.print("Vui long nhap so CCCD: ");
                    // check if CCCD is in correct format
                    while (scanner.hasNext()) {
                        CCCD = scanner.next();
                        if (CCCD.equals("No") || CCCD.equals("no")) {
                            System.exit(0);
                        }
                        // if CCCD is correct format
                        if (CCCD.length() == 12 && CCCD.matches("[0-9]+") && checkCCCD(CCCD) == true) {
                            b = true;
                            break;
                        } else {
                            System.out.println("So CCCD khong hop le.\n" +
                                    "Vui long nhap lai hoac ‘No’ de thoat");
                        }
                    }

                    while (b == true) {
                        notInt = 0;
                        // show submenu
                        menu(1);
                        // check the submenu option if the selection is in the submenu will exit otherwise return to the submenu
                        while (notInt == 0) {
                            String result = scanner.next();
                            if ((result).matches("-?\\d+") && (Integer.parseInt(result) >= 0 && Integer.parseInt(result) <= 3)) {
                                chooseSubMenu = Integer.parseInt(result);
                                notInt = 1;
                                break;
                            } else if (result.equals("menu")) {
                                menu(1);
                            } else {
                                System.out.println("Vui long nhap lai lua chon!\t - \t(Xem lai menu nhap \"menu\")");
                            }
                        }

                        switch (chooseSubMenu) {
                            case 0:
                                System.out.println("Thoat chuong trinh!!");
                                b = false;
                                System.exit(0);
                            case 1:
                                String numberCountry = CCCD.substring(0, 3);
                                System.out.println("Noi sinh: " + showPlaceOfBirth(numberCountry));
                                break;
                            case 2:
                                String numberGender = CCCD.substring(3, 4);
                                String numberAge = CCCD.substring(4, 6);
                                System.out.println("Gioi Tinh: " + showGenderAndAge(numberGender, numberAge));
                                break;
                            case 3:
                                String randomNumber = CCCD.substring(6, 12);
                                System.out.println(randomNumber);
                                break;

                        }
                    }
                }
        }

    }

    // if menuNumber is 0 show mainMenu, if menuNumber is 1 show subMenu
    public static void menu(int menuNumber) {
        if (menuNumber == 0) {
            System.out.println("+----------+------------------------+----------+");
            System.out.println("| NGAN HANG SO | " + AUTHOR + "@" + VERSION + "                |");
            System.out.println("+----------+------------------------+----------+");
            System.out.println("| 1. Nhap CCCD                                 |");
            System.out.println("| 0. Thoat                                     |");
            System.out.println("+----------+------------------------+----------+");
            System.out.println("Chuc Nang:");
        }

        if (menuNumber == 1) {
            System.out.println("    | 1. Kiem tra noi sinh");
            System.out.println("    | 2. Kiem tra tuoi, gioi tinh");
            System.out.println("    | 3. Kiem tra so ngau nhien");
            System.out.println("    | 0. Thoat");
            System.out.println("Chuc nang: ");
        }

        if (menuNumber == 2) {
            System.out.println("Vui long lua chon ma bao mat:");
            System.out.println("    | 1. Ma bao mat Easy");
            System.out.println("    | 2. Ma bao mat Hard");
            System.out.println("    | 0. Thoat");
            System.out.println("Chuc nang: ");
        }
    }

    // check if CCCD is available in the provinces of Vietnam
    public static Boolean checkCCCD(String CCCD) {
        Boolean b = false;
        // convert to Int vd: CCCD = "001" to numberCountry = "1"
        int numberCountry = Integer.parseInt(CCCD.substring(0, 3));

        //code from 001 to 0096 (1 to 96) corresponding to 63 provinces
        if (numberCountry >= 1 && numberCountry <= 96) {
            b = true;
        }
        return b;
    }

    // get country from the first 3 digits of CCCD
    public static String showPlaceOfBirth(String numberCountry) {
        String country = "";
        // Create a HashMap object called people
        HashMap<String, String> Country = new HashMap<String, String>();

        // Add keys and values (Name, Age)
        Country.put("001", "Hà Nội");
        Country.put("002", "Hà Giang");
        Country.put("004", "Cao Bằng");
        Country.put("006", "Bắc Kạn");
        Country.put("008", "Tuyên Quang");
        Country.put("010", "Lào Cai");
        Country.put("011", "Điện Biên");
        Country.put("012", "Lai Châu");
        Country.put("014", "Sơn La");
        Country.put("015", "Yên Bái");
        Country.put("017", "Hòa Bình");
        Country.put("019", "Thái Nguyên");
        Country.put("020", "Lạng Sơn");
        Country.put("022", "Quảng Ninh");
        Country.put("024", "Bắc Giang");
        Country.put("025", "Phú Thọ");
        Country.put("026", "Vĩnh Phúc");
        Country.put("027", "Bắc Ninh");
        Country.put("030", "Hải Dương");
        Country.put("031", "Hải Phòng");
        Country.put("033", "Hưng Yên");
        Country.put("034", "Thái Bình");
        Country.put("035", "Hà Nam");
        Country.put("036", "Nam Định");
        Country.put("037", "Ninh Bình");
        Country.put("038", "Thanh Hóa");
        Country.put("040", "Nghệ An");
        Country.put("042", "Hà Tĩnh");
        Country.put("044", "Quảng Bình");
        Country.put("045", "Quảng Trị");
        Country.put("046", "Thừa Thiên Huế");
        Country.put("048", "Đà Nẵng");
        Country.put("049", "Quảng Nam");
        Country.put("051", "Quảng Ngãi");
        Country.put("052", "Bình Định");
        Country.put("054", "Phú Yên");
        Country.put("056", "Khánh Hòa");
        Country.put("058", "Ninh Thuận");
        Country.put("060", "Bình Thuận");
        Country.put("062", "Kon Tum");
        Country.put("064", "Gia Lai");
        Country.put("066", "Đắk Lắk");
        Country.put("067", "Đắk Nông");
        Country.put("068", "Lâm Đồng");
        Country.put("070", "Bình Phước");
        Country.put("072", "Tây Ninh");
        Country.put("074", "Bình Dương");
        Country.put("075", "Đồng Nai");
        Country.put("077", "Bà Rịa - Vũng Tàu");
        Country.put("079", "Hồ Chí Minh");
        Country.put("080", "Long An");
        Country.put("082", "Tiền Giang");
        Country.put("083", "Bến Tre");
        Country.put("084", "Trà Vinh");
        Country.put("086", "Vĩnh Long");
        Country.put("087", "Đồng Tháp");
        Country.put("089", "An Giang");
        Country.put("091", "Kiên Giang");
        Country.put("092", "Cần Thơ");
        Country.put("093", "Hậu Giang");
        Country.put("094", "Sóc Trăng");
        Country.put("095", "Bạc Liêu");
        Country.put("096", "Cà Mau");

        for (String i : Country.keySet()) {
            if (Objects.equals(i, numberCountry)) {
                country = String.valueOf(Country.get(i));
            } else if (i.compareTo(numberCountry) < 0) {
                country = "Khong co noi sinh trung voi ma " + numberCountry;
            }
        }
        return country;
    }

    // get Gender and Age form function gender() and age()
    public static String showGenderAndAge(String gender, String age) {
        String genderAndAge = gender(Integer.parseInt(gender)) + " | " + age(Integer.parseInt(gender), age);
        return genderAndAge;
    }

    // get Gender form the CCCD (number 4 in 12 number)
    public static String gender(int numberGender) {
        String gender = "";
        // quy luật là các sỗ chẵn đều là nam và các số lẻ là nữ
        if (numberGender % 2 == 0) {
            gender = "Nam";
        } else {
            gender = "Nữ";
        }
        return gender;
    }

    // get year Age form the CCCD (number 5 and 6 in 12 number)
    public static String age(int numberGender, String numberAge) {
        String age = "";

        if (numberGender >= 0 && numberGender <= 1) {
            age = "19" + numberAge;
        }
        if (numberGender >= 2 && numberGender <= 3) {
            age = "20" + numberAge;
        }
        if (numberGender >= 4 && numberGender <= 5) {
            age = "21" + numberAge;
        }
        if (numberGender >= 6 && numberGender <= 7) {
            age = "22" + numberAge;
        }
        if (numberGender >= 8 && numberGender <= 9) {
            age = "23" + numberAge;
        }
        return age;
    }

    // random Number form 100 to 999
    public static Integer randomNumber() {
        Random rand = new Random();
        return rand.ints(100, 999).findFirst().getAsInt();
    }

    // random Alpha Numeric String
    public static String randomNumberString(int n) {
        String result = "";
        String randomString = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghiklmnopqrstuvwxyz";

        for (int i = 0; i < n; i++) {
            Random random = new Random();
            result = result + randomString.charAt(random.nextInt(randomString.length()));
        }
        return result;
    }
}
