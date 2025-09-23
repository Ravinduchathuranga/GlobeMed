package com.rc008code.hms.auth.decorators.service.handlers;

import com.rc008code.hms.auth.decorators.api.UserAuthHandler;
import com.rc008code.hms.auth.decorators.service.model.ValidationResult;
import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.AdminStaffBo;
import com.rc008code.hms.business.custom.PharmacistBo;

public class UserPharmacistAuth extends UserAuthHandler {
    private static final PharmacistBo pharmacistBo = BoFactory.getInstance().getBo(BoFactory.BoType.PHARMACIST);

    @Override
    public ValidationResult authenticate(String username, String password) {
        try {
            boolean b = pharmacistBo.logIn(username, password);
            if (b) {
                return new ValidationResult(true, "pharmacist");
            } else if (nextHandler != null) {
                return nextHandler.authenticate(username, password);
            } else return new ValidationResult(false, "Invalid username or password");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
