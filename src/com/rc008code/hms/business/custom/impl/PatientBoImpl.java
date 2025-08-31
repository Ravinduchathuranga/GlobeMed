package com.rc008code.hms.business.custom.impl;

import com.rc008code.hms.business.custom.PatientBo;
import com.rc008code.hms.dto.PatientDto;
import com.rc008code.hms.entity.Patient;
import com.rc008code.hms.enums.Gender;
import com.rc008code.hms.repository.DaoFactory;
import com.rc008code.hms.repository.custom.PatientDao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PatientBoImpl implements PatientBo {
    private final PatientDao patientDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.PATIENT);

    @Override
    public boolean create(PatientDto entity) throws SQLException, ClassNotFoundException {
        return patientDao.create(toPatient(entity));
    }

    @Override
    public PatientDto read(String id) throws SQLException, ClassNotFoundException {
        Patient patient = patientDao.find(id);
        if (patient != null) {
            return toPatientDto(patient);
        } else {
            return null;
        }
    }

    @Override
    public boolean update(PatientDto entity) throws SQLException, ClassNotFoundException {
        return patientDao.update(toPatient(entity));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return patientDao.delete(id);
    }

    @Override
    public List<PatientDto> readAll() throws SQLException, ClassNotFoundException {
        return patientDao.findAll().stream().map(this::toPatientDto).collect(Collectors.toList());
    }

    @Override
    public List<PatientDto> search(String searchText) throws SQLException, ClassNotFoundException {
        try {
            return patientDao.searchPatient(searchText).stream().map(this::toPatientDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private PatientDto toPatientDto(Patient patient) {
        return new PatientDto(
                patient.getPatientId(),
                patient.getName(),
                patient.getAddress(),
                patient.getAge(),
                patient.getGender(),
                patient.getContact(),
                patient.getEmail()
        );
    }

    private Patient toPatient(PatientDto patientDto) {
        return new Patient(
                patientDto.getPatientId(),
                patientDto.getName(),
                patientDto.getAddress(),
                patientDto.getAge(),
                patientDto.getGender(),
                patientDto.getContact(),
                patientDto.getEmail()
        );
    }
}
