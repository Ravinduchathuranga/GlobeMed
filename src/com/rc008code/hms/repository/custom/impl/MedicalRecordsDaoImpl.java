package com.rc008code.hms.repository.custom.impl;

import com.rc008code.hms.entity.MedicalRecord;
import com.rc008code.hms.repository.CrudUtil;
import com.rc008code.hms.repository.custom.MedicalRecordsDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedicalRecordsDaoImpl implements MedicalRecordsDao {
    @Override
    public boolean create(MedicalRecord medicalRecord) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO medical_record VALUES (?, ?, ?, ?, ?, ?)",
                medicalRecord.getRecord_id(),
                medicalRecord.getPatient_id(),
                medicalRecord.getDoctor_id(),
                medicalRecord.getDiagnosis(),
                medicalRecord.getTreatment(),
                medicalRecord.getRecord_date());
    }

    @Override
    public MedicalRecord find(String entity) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM medical_record WHERE record_id=?", entity);
        if (resultSet.next()) {
            return new MedicalRecord(
                    resultSet.getString("record_id"),
                    resultSet.getString("patient_id"),
                    resultSet.getString("doctor_id"),
                    resultSet.getString("diagnosis"),
                    resultSet.getString("treatment"),
                    resultSet.getDate("record_date")
            );
        }
        return null;
    }

    @Override
    public List<MedicalRecord> findRecordByPatient(String id) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM medical_record where patient_id=?", id);
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        while (resultSet.next()) {
            medicalRecords.add(new MedicalRecord(
                    resultSet.getString("record_id"),
                    resultSet.getString("patient_id"),
                    resultSet.getString("doctor_id"),
                    resultSet.getString("diagnosis"),
                    resultSet.getString("treatment"),
                    resultSet.getDate("record_date")
            ));
        }
        return medicalRecords;
    }

    @Override
    public boolean update(MedicalRecord medicalRecord) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE medical_record SET patient_id=?, doctor_id=?, diagnosis=?, treatment=?, record_date=? WHERE record_id=?";
        return CrudUtil.execute(sql,
                medicalRecord.getPatient_id(),
                medicalRecord.getDoctor_id(),
                medicalRecord.getDiagnosis(),
                medicalRecord.getTreatment(),
                medicalRecord.getRecord_date(),
                medicalRecord.getRecord_id());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM medical_record WHERE record_id=?";
        return CrudUtil.execute(sql, s);
    }

    @Override
    public List<MedicalRecord> findAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM medical_record");
        List<MedicalRecord> medicalRecords = new java.util.ArrayList<>();
        while (resultSet.next()) {
            medicalRecords.add(new MedicalRecord(
                    resultSet.getString("record_id"),
                    resultSet.getString("patient_id"),
                    resultSet.getString("doctor_id"),
                    resultSet.getString("diagnosis"),
                    resultSet.getString("treatment"),
                    resultSet.getDate("record_date")
            ));
        }
        return medicalRecords;
    }

    @Override
    public List<MedicalRecord> searchMedicalRecords(String searchText) throws Exception {
        searchText = "%" + searchText + "%";
        String query = "SELECT * FROM medical_record WHERE doctor_id LIKE ? OR patient_id LIKE ?";
        ResultSet resultSet = CrudUtil.execute(query, searchText, searchText);
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        while (resultSet.next()) {
            medicalRecords.add(new MedicalRecord(
                    resultSet.getString("record_id"),
                    resultSet.getString("patient_id"),
                    resultSet.getString("doctor_id"),
                    resultSet.getString("diagnosis"),
                    resultSet.getString("treatment"),
                    resultSet.getDate("record_date")
            ));
        }
        return medicalRecords;
    }

}
