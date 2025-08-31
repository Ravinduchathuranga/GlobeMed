package com.rc008code.hms.repository.custom.impl;

import com.rc008code.hms.entity.Patient;
import com.rc008code.hms.repository.custom.PatientDao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class PatientDaoImpl implements PatientDao {
    @Override
    public boolean create(Patient patient) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Patient find(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Patient patient) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Patient> findAll() throws SQLException, ClassNotFoundException {
        return Collections.emptyList();
    }
}
