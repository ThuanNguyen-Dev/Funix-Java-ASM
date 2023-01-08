package vn.funix.FX18801.java.ASM02.models;

import static vn.funix.FX18801.java.ASM01.ASM01.checkCCCD;

public abstract class User {

    private String name;
    private String customerId;

    public User(String name, String customerId) {
        this.name = name;
        this.setCustomerId(customerId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    /**
     * Function to help check CCCD
     * @param customerId
     * */
    public void setCustomerId(String customerId) {
        try {
            if (customerId.length() == 12 && customerId.matches("[0-9]+") && checkCCCD(customerId) == true) {
                this.customerId = customerId;
            }
            else {
                throw new Exception();
            }
        }catch (Exception e){
            this.customerId = null;
            if (customerId.equals("No") || customerId.equals("no")) {
                System.exit(0);
            }
            System.out.println("So CCCD khong hop le.\n" +
                    "Vui long nhap lai hoac ‘No’ de thoat");
        }
    }
}
