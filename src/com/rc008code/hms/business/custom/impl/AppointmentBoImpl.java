package com.rc008code.hms.business.custom.impl;

import com.rc008code.hms.business.custom.AppointmentBo;
import com.rc008code.hms.dto.AppointmentDto;
import com.rc008code.hms.dto.DoctorDto;
import com.rc008code.hms.entity.Appointment;
import com.rc008code.hms.repository.DaoFactory;
import com.rc008code.hms.repository.custom.AppointmentDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AppointmentBoImpl implements AppointmentBo {
    private final AppointmentDao appointmentDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.APPOINTMENT);

    @Override
    public boolean create(AppointmentDto entity) throws SQLException, ClassNotFoundException {
        return appointmentDao.create(toAppointment(entity));
    }

    @Override
    public DoctorDto read(String id) throws SQLException, ClassNotFoundException {
        // Not used for appointments in current UI; returning null to keep interface unchanged
        return null;
    }

    @Override
    public boolean update(AppointmentDto entity) throws SQLException, ClassNotFoundException {
        return appointmentDao.update(toAppointment(entity));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return appointmentDao.delete(id);
    }

    @Override
    public List<AppointmentDto> readAll() throws SQLException, ClassNotFoundException {
        return appointmentDao.findAll().stream().map(this::toAppointmentDto).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto> search(String searchText) throws SQLException, ClassNotFoundException {
        String q = searchText == null ? "" : searchText.trim().toLowerCase();
        return readAll().stream().filter(a ->
                a.getAppointmentId().toLowerCase().contains(q)
                        || (Objects.nonNull(a.getPatientId()) && a.getPatientId().toLowerCase().contains(q))
                        || (Objects.nonNull(a.getDoctorId()) && a.getDoctorId().toLowerCase().contains(q))
                        || (Objects.nonNull(a.getDescription()) && a.getDescription().toLowerCase().contains(q))
        ).collect(Collectors.toList());
    }

    private AppointmentDto toAppointmentDto(Appointment a) {
        if (a == null) return null;
        return new AppointmentDto(
                a.getAppointment_id(),
                a.getPatient_id(),
                a.getDoctor_id(),
                a.getAppointment_date(),
                null, // description is not stored at entity/DB level in current model
                a.getStatus()
        );
    }

    private Appointment toAppointment(AppointmentDto d) {
        if (d == null) return null;
        return new Appointment(
                d.getAppointmentId(),
                d.getPatientId(),
                d.getDoctorId(),
                null, // admin staff id not provided by UI
                d.getAppointmentDate(),
                d.getStatus()
        );
    }
}
