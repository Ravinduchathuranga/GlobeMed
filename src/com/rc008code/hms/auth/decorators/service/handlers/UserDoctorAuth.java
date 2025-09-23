package com.rc008code.hms.auth.decorators.service.handlers;

import com.rc008code.hms.auth.decorators.api.UserAuthHandler;
import com.rc008code.hms.auth.decorators.service.model.ValidationResult;
import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.AdminStaffBo;
import com.rc008code.hms.business.custom.DoctorBo;

public class UserDoctorAuth extends UserAuthHandler {
    private static final DoctorBo doctorBo = BoFactory.getInstance().getBo(BoFactory.BoType.DOCTOR);

    @Override
    public ValidationResult authenticate(String username, String password) {
        try {
            boolean b = doctorBo.logIn(username, password);
            if (b) {
                return new ValidationResult(true,"doctor");
            } else if (nextHandler != null) {
                return nextHandler.authenticate(username, password);
            } else return new ValidationResult(false,"Invalid username or password");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
