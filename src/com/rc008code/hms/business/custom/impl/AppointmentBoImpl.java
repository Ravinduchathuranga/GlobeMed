package com.rc008code.hms.business.custom.impl;

import com.rc008code.hms.business.custom.AppointmentBo;
import com.rc008code.hms.dto.AppointmentDto;
import com.rc008code.hms.dto.DoctorDto;
import com.rc008code.hms.entity.Appointment;
import com.rc008code.hms.entity.Doctor;
import com.rc008code.hms.repository.DaoFactory;
import com.rc008code.hms.repository.custom.AppointmentDao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class AppointmentBoImpl implements AppointmentBo {
    private AppointmentDao appointmentDao= DaoFactory.getInstance().getDao(DaoFactory.DaoType.APPOINTMENT);
    @Override
    public boolean create(AppointmentDto entity) throws SQLException, ClassNotFoundException {
        return appointmentDao.create(toAppointment(entity));
    }

    @Override
    public DoctorDto read(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(AppointmentDto entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<AppointmentDto> readAll() throws SQLException, ClassNotFoundException {
        return Collections.emptyList();
    }

    @Override
    public List<AppointmentDto> search(String searchText) throws SQLException, ClassNotFoundException {
        return Collections.emptyList();
    }

    private AppointmentDto toAppointmentDto(Appointment appointment) {
        return new AppointmentDto(

        );
    }

    private Appointment toAppointment(AppointmentDto appointmentDto) {
        return new Appointment(

        );
    }
}
