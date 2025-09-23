package com.rc008code.hms.auth.decorators.service.handlers;

import com.rc008code.hms.auth.decorators.api.UserAuthHandler;
import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.AdminStaffBo;
import com.rc008code.hms.business.custom.NurseBo;

public class UserNurseAuth extends UserAuthHandler {
    private static final NurseBo nurseBo = BoFactory.getInstance().getBo(BoFactory.BoType.NURSE);

    @Override
    public boolean authenticate(String username, String password) {
        try {
            boolean b = nurseBo.logIn(username, password);
            if (b) {
                return true;
            } else if (nextHandler != null) {
                return nextHandler.authenticate(username, password);
            } else return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
