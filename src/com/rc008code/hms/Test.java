package com.rc008code.hms;
import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.AdminStaffBo;

public class Test {
    private final static AdminStaffBo adminStaffBo= BoFactory.getInstance().getBo(BoFactory.BoType.ADMINSTAFF);
    public static void main(String[] args) {
        try {
            boolean isLogin= adminStaffBo.logIn("ravindu","ravindu");
            System.out.println(isLogin);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
