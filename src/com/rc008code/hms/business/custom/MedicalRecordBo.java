package com.rc008code.hms.business.custom;

import com.rc008code.hms.dto.MedicalRecordDto;

import java.sql.SQLException;
import java.util.List;

public interface MedicalRecordBo {
    public boolean create(MedicalRecordDto medicalRecordDto) throws SQLException, ClassNotFoundException;

    public MedicalRecordDto find(String id) throws SQLException, ClassNotFoundException;
    List<MedicalRecordDto> findRecordByPatient(String id) throws Exception;


    public boolean update(MedicalRecordDto medicalRecordDto) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<MedicalRecordDto> readAll() throws SQLException, ClassNotFoundException;

    public List<MedicalRecordDto> searchMedicalRecords(String searchText) throws Exception;

}
