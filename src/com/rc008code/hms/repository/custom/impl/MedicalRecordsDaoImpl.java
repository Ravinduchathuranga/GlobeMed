package com.rc008code.hms.repository.custom.impl;

import com.rc008code.hms.entity.MedicalRecord;
import com.rc008code.hms.repository.CrudUtil;
import com.rc008code.hms.repository.custom.MedicalRecordsDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List//        // Get Doctor
//        MedicalRecordDto retrievedDoctor = medicalRecordBo.read("212121");
//        System.out.println("Get Doctor: " + (retrievedDoctor != null ? "SUCCESS" : "FAILED"));
//
//        // Update Doctor
//        if (retrievedDoctor != null) {
//            retrievedDoctor.setDiagnosis("Dr. Smith Updated");
//            boolean updated = medicalRecordBo.update(retrievedDoctor);
//            System.out.println("Update Doctor: " + (updated ? "SUCCESS" : "FAILED"));
//        }
//
//        // List all Doctors
//        List<MedicalRecordDto> doctors = medicalRecordBo.readAll();
//        System.out.println("List Doctors: Found " + doctors.size() + " doctors");
//
//        // Cleanup
//        boolean deleted = medicalRecordBo.delete("212121");
//        System.out.println("Delete Doctor: " + (deleted ? "SUCCESS" : "FAILED"));
;

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
