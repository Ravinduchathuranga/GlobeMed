package com.rc008code.hms.repository.custom.impl;

import com.rc008code.hms.database.MysqlConnection;
import com.rc008code.hms.entity.Patient;
import com.rc008code.hms.enums.Gender;
import com.rc008code.hms.repository.CrudUtil;
import com.rc008code.hms.repository.custom.PatientDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PatientDaoImpl implements PatientDao {
    @Override
    public boolean create(Patient patient) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO patient VALUES (?, ?, ?, ?, ?, ?, ?)",
                patient.getPatientId(),
                patient.getName(),
                patient.getAddress(),
                patient.getAge(),
                patient.getGender().toString(),
                patient.getContact(),
                patient.getEmail());
    }

    @Override
    public Patient find(String entity) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM patient WHERE patient_id=?", entity);
        if (resultSet.next()) {
            return new Patient(
                    resultSet.getString("patient_id"),
                    resultSet.getString("patient_name"),
                    resultSet.getString("address"),
                    resultSet.getInt("age"),
                    Gender.valueOf(resultSet.getString("gender")),
                    resultSet.getString("contact_no"),
                    resultSet.getString("email"));
        }
        return null;
    }

    @Override
    public boolean update(Patient patient) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE patient SET patient_name=?,address=?,age=?,gender=?,contact_no=?,email=? WHERE patient_id=?";
        return CrudUtil.execute(sql, patient.getName(), patient.getAddress(), patient.getAge(), patient.getGender().toString(), patient.getContact(), patient.getEmail(), patient.getPatientId());
    }

    @Override
    public boolean delete(String entity) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM patient WHERE patient_Id=?";
        return CrudUtil.execute(sql, entity);
    }

    @Override
    public List<Patient> findAll() throws SQLException, ClassNotFoundException {
        return Collections.emptyList();
    }

    @Override
    public List<Patient> searchPatient(String searchText) throws Exception {
        searchText = "%" + searchText + "%";
        String query = "SELECT * FROM patient WHERE patient_name LIKE ? OR email LIKE ?";
        ResultSet resultSet = CrudUtil.execute(query, searchText, searchText);
        List<Patient> patientsArrayList = new ArrayList<>();
        while (resultSet.next()) {
            patientsArrayList.add(
                    new Patient(
                            resultSet.getString("patient_id"),
                            resultSet.getString("patient_name"),
                            resultSet.getString("address"),
                            resultSet.getInt("age"),
                            Gender.valueOf(resultSet.getString("gender")),
                            resultSet.getString("contact_no"),
                            resultSet.getString("email")
                    )
            );
        }
        return patientsArrayList;
    }
}
