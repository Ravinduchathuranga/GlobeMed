package com.rc008code.hms.repository.custom;

import com.rc008code.hms.entity.Doctor;
import com.rc008code.hms.entity.Prescription;
import com.rc008code.hms.repository.CrudDao;

import java.sql.SQLException;
import java.util.List;

public interface PrescriptionDao extends CrudDao<Prescription, String> {
    List<Prescription> findByPatientId(String patientId) throws SQLException, ClassNotFoundException;

    List<Prescription> findByRecordId(String recordId) throws SQLException, ClassNotFoundException;

    List<Prescription> searchPrescription(String searchText) throws Exception;

}
