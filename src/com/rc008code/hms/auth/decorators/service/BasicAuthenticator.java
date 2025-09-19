package com.rc008code.hms.auth.decorators.service;

import com.rc008code.hms.auth.decorators.api.Authenticator;
import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.AdminStaffBo;
import com.rc008code.hms.business.custom.impl.AdminStaffBoImpl;
import com.rc008code.hms.util.CommonUtil;

public class BasicAuthenticator implements Authenticator {

    //dao logic here
    private static final AdminStaffBo adminStaffBo = BoFactory.getInstance().getBo(BoFactory.BoType.ADMINSTAFF);

    @Override
    public boolean authenticate(String email, String password) {
        try {
            boolean isLogIn = adminStaffBo.logIn(email, password);
            return isLogIn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
