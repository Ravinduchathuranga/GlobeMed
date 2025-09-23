package com.rc008code.hms.repository.custom.impl;

import com.rc008code.hms.database.MysqlConnection;
import com.rc008code.hms.entity.Doctor;
import com.rc008code.hms.enums.Departments;
import com.rc008code.hms.repository.CrudUtil;
import com.rc008code.hms.repository.custom.DoctorDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {
    @Override
    public boolean create(Doctor doctor) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO doctor VALUES (?, ?, ?, ?, ?, ?, ?)",
                doctor.getDoctorId(),
                doctor.getName(),
                doctor.getSpecialty(),
                doctor.getContact(),
                doctor.getDepartment().toString(),
                doctor.getEmail(),
                doctor.getPassword());
    }

    @Override
    public Doctor find(String entity) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM doctor WHERE doctor_id=?", entity);
        if (resultSet.next()) {
            return new Doctor(
                    resultSet.getString("doctor_id"),
                    resultSet.getString("doctor_name"),
                    resultSet.getString("specialty"),
                    resultSet.getString("contact_no"),
                    Departments.valueOf(resultSet.getString("department")),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
        }
        return null;
    }

    @Override
    public boolean update(Doctor doctor) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE doctor SET doctor_name=?, specialty=?, contact_no=?, department=?, email=?, password=? WHERE doctor_id=?";
        return CrudUtil.execute(sql,
                doctor.getName(),
                doctor.getSpecialty(),
                doctor.getContact(),
                doctor.getDepartment().toString(),
                doctor.getEmail(),
                doctor.getPassword(),
                doctor.getDoctorId()
        );
    }

    @Override
    public boolean delete(String entity) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM doctor WHERE doctor_id=?";
        return CrudUtil.execute(sql, entity);
    }

    @Override
    public List<Doctor> findAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM doctor");
        List<Doctor> doctors = new ArrayList<>();
        while (resultSet.next()) {
            doctors.add(new Doctor(
                    resultSet.getString("doctor_id"),
                    resultSet.getString("doctor_name"),
                    resultSet.getString("specialty"),
                    resultSet.getString("contact_no"),
                    Departments.valueOf(resultSet.getString("department")),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            ));
        }
        return doctors;
    }

    @Override
    public boolean logIn(String email, String password) throws Exception {
        String query = "SELECT * FROM doctor WHERE email= '" + email + "' AND password = '" + password + "'";
        Statement statement = MysqlConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Doctor> searchDoctors(String searchText) throws Exception {
        searchText = "%" + searchText + "%";
        String query = "SELECT * FROM doctor WHERE doctor_name LIKE ? OR email LIKE ?";
        ResultSet resultSet = CrudUtil.execute(query, searchText, searchText);
        List<Doctor> doctors = new ArrayList<>();
        while (resultSet.next()) {
            doctors.add(new Doctor(
                    resultSet.getString("doctor_id"),
                    resultSet.getString("doctor_name"),
                    resultSet.getString("specialty"),
                    resultSet.getString("contact_no"),
                    Departments.valueOf(resultSet.getString("department")),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            ));
        }
        return doctors;
    }
}
