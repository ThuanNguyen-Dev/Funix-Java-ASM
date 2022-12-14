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
                    int status = 0;
                    System.out.print("Vui long nhap so CCCD: ");
                    // check if CCCD is in correct format
                    while (scanner.hasNext()) {
                        CCCD = scanner.next();
                        // if CCCD is correct format
                        if (CCCD.length() == 12 && CCCD.matches("[0-9]+") && checkCCCD(CCCD) == true) {
                            b = true;
                            break;
                        } else {
                            if (status >= 1) {
                                if (CCCD.equals("No") || CCCD.equals("no")) {
                                    System.exit(0);
                                }
                            }
                            System.out.println("So CCCD khong hop le.\n" +
                                    "Vui long nhap lai hoac ???No??? de thoat");
                            status++;
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
        Country.put("001", "H?? N???i");
        Country.put("002", "H?? Giang");
        Country.put("004", "Cao B???ng");
        Country.put("006", "B???c K???n");
        Country.put("008", "Tuy??n Quang");
        Country.put("010", "L??o Cai");
        Country.put("011", "??i???n Bi??n");
        Country.put("012", "Lai Ch??u");
        Country.put("014", "S??n La");
        Country.put("015", "Y??n B??i");
        Country.put("017", "H??a B??nh");
        Country.put("019", "Th??i Nguy??n");
        Country.put("020", "L???ng S??n");
        Country.put("022", "Qu???ng Ninh");
        Country.put("024", "B???c Giang");
        Country.put("025", "Ph?? Th???");
        Country.put("026", "V??nh Ph??c");
        Country.put("027", "B???c Ninh");
        Country.put("030", "H???i D????ng");
        Country.put("031", "H???i Ph??ng");
        Country.put("033", "H??ng Y??n");
        Country.put("034", "Th??i B??nh");
        Country.put("035", "H?? Nam");
        Country.put("036", "Nam ?????nh");
        Country.put("037", "Ninh B??nh");
        Country.put("038", "Thanh H??a");
        Country.put("040", "Ngh??? An");
        Country.put("042", "H?? T??nh");
        Country.put("044", "Qu???ng B??nh");
        Country.put("045", "Qu???ng Tr???");
        Country.put("046", "Th???a Thi??n Hu???");
        Country.put("048", "???? N???ng");
        Country.put("049", "Qu???ng Nam");
        Country.put("051", "Qu???ng Ng??i");
        Country.put("052", "B??nh ?????nh");
        Country.put("054", "Ph?? Y??n");
        Country.put("056", "Kh??nh H??a");
        Country.put("058", "Ninh Thu???n");
        Country.put("060", "B??nh Thu???n");
        Country.put("062", "Kon Tum");
        Country.put("064", "Gia Lai");
        Country.put("066", "?????k L???k");
        Country.put("067", "?????k N??ng");
        Country.put("068", "L??m ?????ng");
        Country.put("070", "B??nh Ph?????c");
        Country.put("072", "T??y Ninh");
        Country.put("074", "B??nh D????ng");
        Country.put("075", "?????ng Nai");
        Country.put("077", "B?? R???a - V??ng T??u");
        Country.put("079", "H??? Ch?? Minh");
        Country.put("080", "Long An");
        Country.put("082", "Ti???n Giang");
        Country.put("083", "B???n Tre");
        Country.put("084", "Tr?? Vinh");
        Country.put("086", "V??nh Long");
        Country.put("087", "?????ng Th??p");
        Country.put("089", "An Giang");
        Country.put("091", "Ki??n Giang");
        Country.put("092", "C???n Th??");
        Country.put("093", "H???u Giang");
        Country.put("094", "S??c Tr??ng");
        Country.put("095", "B???c Li??u");
        Country.put("096", "C?? Mau");

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
        // quy lu???t l?? c??c s??? ch???n ?????u l?? nam v?? c??c s??? l??? l?? n???
        if (numberGender % 2 == 0) {
            gender = "Nam";
        } else {
            gender = "N???";
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
