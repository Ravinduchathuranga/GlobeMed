package com.rc008code.hms.repository.custom.impl;

import com.rc008code.hms.entity.Appointment;
import com.rc008code.hms.repository.custom.AppointmentDao;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class AppointmentDaoImpl implements AppointmentDao {
    private static final Map<String, Appointment> STORE = new LinkedHashMap<>();

    @Override
    public boolean create(Appointment appointment) throws SQLException, ClassNotFoundException {
        if (appointment == null || appointment.getAppointment_id() == null) return false;
        if (STORE.containsKey(appointment.getAppointment_id())) return false;
        STORE.put(appointment.getAppointment_id(), appointment);
        return true;
    }

    @Override
    public Appointment find(String id) throws SQLException, ClassNotFoundException {
        return STORE.get(id);
    }

    @Override
    public boolean update(Appointment appointment) throws SQLException, ClassNotFoundException {
        if (appointment == null || appointment.getAppointment_id() == null) return false;
        if (!STORE.containsKey(appointment.getAppointment_id())) return false;
        STORE.put(appointment.getAppointment_id(), appointment);
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return STORE.remove(id) != null;
    }

    @Override
    public List<Appointment> findAll() throws SQLException, ClassNotFoundException {
        return new ArrayList<>(STORE.values());
    }
}
