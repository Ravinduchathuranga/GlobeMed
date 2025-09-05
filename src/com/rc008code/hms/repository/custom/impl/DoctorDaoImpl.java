package com.rc008code.hms.repository.custom.impl;

import com.rc008code.hms.entity.Doctor;
import com.rc008code.hms.repository.custom.DoctorDao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {
    @Override
    public boolean create(Doctor doctor) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Doctor find(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Doctor doctor) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Doctor> findAll() throws SQLException, ClassNotFoundException {
        return Collections.emptyList();
    }
}
