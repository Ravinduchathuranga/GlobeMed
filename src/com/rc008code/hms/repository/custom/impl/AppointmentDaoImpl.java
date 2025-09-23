package com.rc008code.hms.repository.custom.impl;

import com.rc008code.hms.entity.Appointment;
import com.rc008code.hms.repository.custom.AppointmentDao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class AppointmentDaoImpl implements AppointmentDao {
    @Override
    public boolean create(Appointment appointment) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Appointment find(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Appointment appointment) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Appointment> findAll() throws SQLException, ClassNotFoundException {
        return Collections.emptyList();
    }
}
