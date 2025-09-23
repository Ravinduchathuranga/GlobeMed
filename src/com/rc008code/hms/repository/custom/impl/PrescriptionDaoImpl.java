package com.rc008code.hms.repository.custom.impl;

import com.rc008code.hms.entity.Doctor;
import com.rc008code.hms.entity.MedicalRecord;
import com.rc008code.hms.entity.Prescription;
import com.rc008code.hms.enums.Departments;
import com.rc008code.hms.repository.CrudUtil;
import com.rc008code.hms.repository.custom.PrescriptionDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrescriptionDaoImpl implements PrescriptionDao {
    @Override
    public boolean create(Prescription prescription) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO prescription (prescription_id, patient_id, record_id, doctor_id, date, medicines, notes) VALUES (?, ?, ?, ?, ?, ?, ?)",
                prescription.getPrescription_id(),
                prescription.getRecord_id(),
                prescription.getPatient_id(),
                prescription.getPharmacist_id(),
                prescription.getMedication(),
                prescription.getDosage(),
                prescription.getIssue_date());
    }

    @Override
    public Prescription find(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM prescription WHERE doctor_id=?", s);
        if (resultSet.next()) {
            return new Prescription(
                    resultSet.getString("prescription_id"),
                    resultSet.getString("record_id"),
                    resultSet.getString("pharmacist_id"),
                    resultSet.getString("patient_id"),
                    resultSet.getString("medication"),
                    resultSet.getString("dosage"),
                    resultSet.getDate("issue_date"));
        }
        return null;
    }

    @Override
    public List<Prescription> findByPatientId(String patientId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM prescription where patient_id=?", patientId);
        List<Prescription> prescriptions = new ArrayList<>();
        while (resultSet.next()) {
            prescriptions.add(new Prescription(
                    resultSet.getString("prescription_id"),
                    resultSet.getString("record_id"),
                    resultSet.getString("pharmacist_id"),
                    resultSet.getString("patient_id"),
                    resultSet.getString("medication"),
                    resultSet.getString("dosage"),
                    resultSet.getDate("issue_date")));
        }
        return prescriptions;
    }

    @Override
    public List<Prescription> findByRecordId(String recordId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM prescription where patient_id=?", recordId);
        List<Prescription> prescriptions = new ArrayList<>();
        while (resultSet.next()) {
            prescriptions.add(new Prescription(
                    resultSet.getString("prescription_id"),
                    resultSet.getString("record_id"),
                    resultSet.getString("pharmacist_id"),
                    resultSet.getString("patient_id"),
                    resultSet.getString("medication"),
                    resultSet.getString("dosage"),
                    resultSet.getDate("issue_date")));
        }
        return prescriptions;
    }

    @Override
    public boolean update(Prescription prescription) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE prescription SET medicines=?, dosage=? WHERE prescription_id=?";
        return CrudUtil.execute(sql,
                prescription.getDosage(),
                prescription.getMedication()
        );
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM prescription WHERE prescription_id=?";
        return CrudUtil.execute(sql, s);
    }

    @Override
    public List<Prescription> findAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM prescription");
        List<Prescription> prescription = new ArrayList<>();
        while (resultSet.next()) {
            prescription.add(new Prescription(
                    resultSet.getString("prescription_id"),
                    resultSet.getString("record_id"),
                    resultSet.getString("pharmacist_id"),
                    resultSet.getString("patient_id"),
                    resultSet.getString("medication"),
                    resultSet.getString("dosage"),
                    resultSet.getDate("issue_date")));
        }
        return prescription;
    }

    @Override
    public List<Prescription> searchPrescription(String searchText) throws Exception {
        searchText = "%" + searchText + "%";
        String query = "SELECT * FROM prescription WHERE patient_id LIKE ? OR doctor_id LIKE ?";
        ResultSet resultSet = CrudUtil.execute(query, searchText, searchText);
        List<Prescription> prescriptions = new ArrayList<>();
        while (resultSet.next()) {
            prescriptions.add(new Prescription(
                    resultSet.getString("prescription_id"),
                    resultSet.getString("record_id"),
                    resultSet.getString("pharmacist_id"),
                    resultSet.getString("patient_id"),
                    resultSet.getString("medication"),
                    resultSet.getString("dosage"),
                    resultSet.getDate("issue_date")));
        }
        return prescriptions;
    }
}
