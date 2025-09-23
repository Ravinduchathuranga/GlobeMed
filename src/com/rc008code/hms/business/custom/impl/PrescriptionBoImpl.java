package com.rc008code.hms.business.custom.impl;

import com.rc008code.hms.business.custom.PrescriptionBo;
import com.rc008code.hms.dto.PrescriptionDto;
import com.rc008code.hms.entity.Prescription;
import com.rc008code.hms.repository.DaoFactory;
import com.rc008code.hms.repository.custom.PrescriptionDao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PrescriptionBoImpl implements PrescriptionBo {
    private final PrescriptionDao prescriptionDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.PRESCRIPTION);

    @Override
    public boolean create(PrescriptionDto entity) throws SQLException, ClassNotFoundException {
        return prescriptionDao.create(toPrescription(entity));
    }

    @Override
    public PrescriptionDto find(String id) throws SQLException, ClassNotFoundException {
        Prescription prescription = prescriptionDao.find(id);
        if (prescription != null) {
            return toPrescriptionDto(prescription);
        } else {
            return null;
        }
    }

    @Override
    public boolean update(PrescriptionDto entity) throws SQLException, ClassNotFoundException {
        return prescriptionDao.update(toPrescription(entity));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return prescriptionDao.delete(id);
    }

    @Override
    public List<PrescriptionDto> readAll() throws SQLException, ClassNotFoundException {
        return prescriptionDao.findAll().stream().map(this::toPrescriptionDto).collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionDto> findByPatientId(String patientId) throws SQLException, ClassNotFoundException {
        try {
            return prescriptionDao.findByPatientId(patientId).stream().map(this::toPrescriptionDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PrescriptionDto> findByRecordId(String recordId) {
        try {
            return prescriptionDao.findByPatientId(recordId).stream().map(this::toPrescriptionDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<PrescriptionDto> searchPrescription(String searchText) throws Exception {
        try {
            return prescriptionDao.searchPrescription(searchText).stream().map(this::toPrescriptionDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private PrescriptionDto toPrescriptionDto(Prescription prescription) {
        return new PrescriptionDto(
                prescription.getPrescription_id(),
                prescription.getRecord_id(),
                prescription.getPatient_id(),
                prescription.getPharmacist_id(),
                prescription.getMedication(),
                prescription.getDosage(),
                prescription.getIssue_date());
    }

    private Prescription toPrescription(PrescriptionDto prescriptionDto) {
        return new Prescription(
                prescriptionDto.getPrescription_id(),
                prescriptionDto.getRecord_id(),
                prescriptionDto.getPatient_id(),
                prescriptionDto.getPharmacist_id(),
                prescriptionDto.getMedication(),
                prescriptionDto.getDosage(),
                prescriptionDto.getIssue_date());
    }
}
