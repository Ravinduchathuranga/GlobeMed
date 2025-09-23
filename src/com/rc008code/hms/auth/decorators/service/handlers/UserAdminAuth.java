package com.rc008code.hms.auth.decorators.service.handlers;

import com.rc008code.hms.auth.decorators.api.UserAuthHandler;
import com.rc008code.hms.auth.decorators.service.model.ValidationResult;
import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.AdminStaffBo;

public class UserAdminAuth extends UserAuthHandler {

    private static final AdminStaffBo adminStaffBo = BoFactory.getInstance().getBo(BoFactory.BoType.ADMINSTAFF);

    @Override
    public ValidationResult authenticate(String username, String password) {
        try {
            boolean b = adminStaffBo.logIn(username, password);
            if (b) {
                return new ValidationResult(true, "AdminDashboard");
            } else if (nextHandler != null) {
                return nextHandler.authenticate(username, password);
            } else return new ValidationResult(false, "Invalid username or password");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
