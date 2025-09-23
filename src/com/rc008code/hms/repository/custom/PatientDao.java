package com.rc008code.hms.repository.custom;

import com.rc008code.hms.entity.Patient;
import com.rc008code.hms.repository.CrudDao;

import java.util.List;

public interface PatientDao extends CrudDao<Patient, String> {
    public List<Patient> searchPatient(String searchText) throws Exception;
}
