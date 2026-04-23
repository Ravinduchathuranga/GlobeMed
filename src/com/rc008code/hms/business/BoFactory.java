package com.rc008code.hms.business;

import com.rc008code.hms.business.custom.MedicalRecordBo;
import com.rc008code.hms.business.custom.PrescriptionBo;
import com.rc008code.hms.business.custom.impl.*;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public enum BoType {
        DOCTOR, NURSE, PATIENT, PHARMACIST, ADMINSTAFF, APPOINTMENT, MEDICINE, MEDICALRECORD, PRESCRIPTION, PAYMENT

    }

    public static BoFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    @SuppressWarnings("unchecked")
    public <T> T getBo(BoType type) {
        switch (type) {
            case DOCTOR:
                return (T) new DoctorBoImpl();
            case NURSE:
                return (T) new NurseBoImpl();
            case PATIENT:
                return (T) new PatientBoImpl();
            case PHARMACIST:
                return (T) new PharmacistBoImpl();
            case APPOINTMENT:
                return (T) new AppointmentBoImpl();
            case ADMINSTAFF:
                return (T) new AdminStaffBoImpl();
            case MEDICALRECORD:
                return (T) new MedicalRecordBoImpl();
            case PRESCRIPTION:
                return (T) new PrescriptionBoImpl();
            case PAYMENT:
                return (T) new PaymentBoImpl();
            default:
                throw new IllegalArgumentException("Unknown BO type: " + type);
        }
    }
}
