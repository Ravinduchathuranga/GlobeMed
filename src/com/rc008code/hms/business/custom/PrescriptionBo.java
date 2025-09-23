package com.rc008code.hms.business.custom;

import com.rc008code.hms.dto.PrescriptionDto;
import com.rc008code.hms.entity.Prescription;

import java.sql.SQLException;
import java.util.List;

public interface PrescriptionBo {
    public boolean create(PrescriptionDto entity) throws SQLException, ClassNotFoundException;

    public PrescriptionDto find(String id) throws SQLException, ClassNotFoundException;

    public boolean update(PrescriptionDto entity) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<PrescriptionDto> readAll() throws SQLException, ClassNotFoundException;

    List<PrescriptionDto> findByPatientId(String patientId) throws SQLException, ClassNotFoundException;

    List<PrescriptionDto> findByRecordId(String recordId);

    public List<PrescriptionDto> searchPrescription(String searchText) throws Exception;


}
