package com.rc008code.hms.repository;

import com.rc008code.hms.repository.custom.impl.*;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public enum DaoType {
        DOCTOR, NURSE, PATIENT, PHARMACIST, ADMINSTAFF, APPOINTMENT, MEDICINE
    }

    @SuppressWarnings("unchecked")
    public <T> T getDao(DaoType daoType) {
        switch (daoType) {
            case DOCTOR:
                return (T) new DoctorDaoImpl();
            case NURSE:
                return (T) new NurseDaoImpl();
            case PATIENT:
                return (T) new PatientDaoImpl();
            case PHARMACIST:
                return (T) new PharmacistDaoImpl();
            case ADMINSTAFF:
                return (T) new AdminStaffDaoImpl();
//            case APPOINTMENT:
//                return (T) new AppointmentDaoImpl();
//            case MEDICINE:
//                return (T) new MedicineDaoImpl();
            default:
                throw new IllegalArgumentException("Unknown DAO type: " + daoType);
        }
    }
}
