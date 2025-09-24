package com.rc008code.hms.repository.custom.impl;

import com.rc008code.hms.entity.Appointment;
import com.rc008code.hms.enums.AppointmentStatus;
import com.rc008code.hms.repository.CrudUtil;
import com.rc008code.hms.repository.custom.AppointmentDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoImpl implements AppointmentDao {

    @Override
    public boolean create(Appointment appointment) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO appointment (appointment_id, patient_id, doctor_id, admin_staff_id, appointment_date, description, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql,
                appointment.getAppointment_id(),
                appointment.getPatient_id(),
                appointment.getDoctor_id(),
                appointment.getAdmin_staff_id(),
                new Timestamp(appointment.getAppointment_date().getTime()),
                appointment.getDescription(),
                appointment.getStatus().toString()
        );
    }

    @Override
    public Appointment find(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM appointment WHERE appointment_id=?", id);
        if (rs.next()) {
            return mapRow(rs);
        }
        return null;
    }

    @Override
    public boolean update(Appointment appointment) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE appointment SET patient_id=?, doctor_id=?, admin_staff_id=?, appointment_date=?, description=?, status=? WHERE appointment_id=?";
        return CrudUtil.execute(sql,
                appointment.getPatient_id(),
                appointment.getDoctor_id(),
                appointment.getAdmin_staff_id(),
                new Timestamp(appointment.getAppointment_date().getTime()),
                appointment.getDescription(),
                appointment.getStatus().toString(),
                appointment.getAppointment_id()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM appointment WHERE appointment_id=?", id);
    }

    @Override
    public List<Appointment> findAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM appointment");
        List<Appointment> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        return list;
    }

    private Appointment mapRow(ResultSet rs) throws SQLException {
        Appointment ap = new Appointment(
                rs.getString("appointment_id"),
                rs.getString("patient_id"),
                rs.getString("doctor_id"),
                rs.getString("admin_staff_id"),
                rs.getTimestamp("appointment_date"),
                rs.getString("description"),
                AppointmentStatus.valueOf(rs.getString("status"))
        );
        return ap;
    }
}
