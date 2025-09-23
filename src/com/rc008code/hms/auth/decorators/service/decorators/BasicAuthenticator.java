package com.rc008code.hms.auth.decorators.service.decorators;

import com.rc008code.hms.auth.decorators.api.Authenticator;
import com.rc008code.hms.auth.decorators.service.handlers.UserAdminAuth;
import com.rc008code.hms.auth.decorators.service.handlers.UserDoctorAuth;
import com.rc008code.hms.auth.decorators.service.handlers.UserNurseAuth;
import com.rc008code.hms.auth.decorators.service.handlers.UserPharmacistAuth;
import com.rc008code.hms.auth.decorators.service.model.ValidationResult;
import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.AdminStaffBo;

public class BasicAuthenticator implements Authenticator {
    @Override
    public ValidationResult authenticate(String email, String password) {
        UserAdminAuth userAdminAuth = new UserAdminAuth();
        UserDoctorAuth userDoctorAuth = new UserDoctorAuth();
        UserNurseAuth userNurseAuth = new UserNurseAuth();
        UserPharmacistAuth userPharmacistAuth = new UserPharmacistAuth();

        userAdminAuth.setNextHandler(userDoctorAuth);
        userDoctorAuth.setNextHandler(userNurseAuth);
        userNurseAuth.setNextHandler(userPharmacistAuth);

        return userAdminAuth.authenticate(email, password);
    }
}
