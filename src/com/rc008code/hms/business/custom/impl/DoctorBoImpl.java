package com.rc008code.hms.business.custom.impl;

import com.rc008code.hms.business.custom.DoctorBo;
import com.rc008code.hms.dto.DoctorDto;
import com.rc008code.hms.entity.Doctor;
import com.rc008code.hms.repository.DaoFactory;
import com.rc008code.hms.repository.custom.DoctorDao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorBoImpl implements DoctorBo {

    private final DoctorDao doctorDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.DOCTOR);

    @Override
    public boolean create(DoctorDto entity) throws SQLException, ClassNotFoundException {
        return doctorDao.create(toDoctor(entity));
    }

    @Override
    public DoctorDto read(String id) throws SQLException, ClassNotFoundException {
        Doctor doctor = doctorDao.find(id);
        if (doctor != null) {
            return toDoctorDto(doctor);
        } else {
            return null;
        }
    }

    @Override
    public boolean update(DoctorDto entity) throws SQLException, ClassNotFoundException {
        return doctorDao.update(toDoctor(entity));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return doctorDao.delete(id);
    }

    @Override
    public List<DoctorDto> readAll() throws SQLException, ClassNotFoundException {
        return doctorDao.findAll().stream().map(this::toDoctorDto).collect(Collectors.toList());
    }

    @Override
    public List<DoctorDto> search(String searchText) throws SQLException, ClassNotFoundException {
        try {
            return doctorDao.searchDoctors(searchText).stream().map(this::toDoctorDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean logIn(String username, String password) throws Exception {
        return doctorDao.logIn(username, password);
    }


    private DoctorDto toDoctorDto(Doctor doctor) {
        return new DoctorDto(
                doctor.getDoctorId(),
                doctor.getName(),
                doctor.getSpecialty(),
                doctor.getContact(),
                doctor.getDepartment(),
                doctor.getEmail(),
                doctor.getPassword()
        );
    }

    private Doctor toDoctor(DoctorDto doctorDto) {
        return new Doctor(
                doctorDto.getDoctorId(),
                doctorDto.getName(),
                doctorDto.getSpecialty(),
                doctorDto.getContact(),
                doctorDto.getDepartment(),
                doctorDto.getEmail(),
                doctorDto.getPassword()
        );
    }
}
