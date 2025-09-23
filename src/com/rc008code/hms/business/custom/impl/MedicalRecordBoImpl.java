package com.rc008code.hms.business.custom.impl;

import com.rc008code.hms.business.custom.MedicalRecordBo;
import com.rc008code.hms.dto.MedicalRecordDto;
import com.rc008code.hms.entity.MedicalRecord;
import com.rc008code.hms.repository.DaoFactory;
import com.rc008code.hms.repository.custom.MedicalRecordsDao;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class MedicalRecordBoImpl implements MedicalRecordBo {
    private static final MedicalRecordsDao medicalRecordsDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.MEDICALRECORD);

    @Override
    public boolean create(MedicalRecordDto medicalRecordDto) throws SQLException, ClassNotFoundException {
        return medicalRecordsDao.create(toMedicalRecord(medicalRecordDto));
    }

    @Override
    public MedicalRecordDto find(String id) throws SQLException, ClassNotFoundException {
        MedicalRecord medicalRecord = medicalRecordsDao.find(id);
        if (medicalRecord != null) {
            return toMedicalRecordDto(medicalRecord);
        }
        return null;
    }

    @Override
    public List<MedicalRecordDto> findRecordByPatient(String id) throws Exception {
        try {
            return medicalRecordsDao.findRecordByPatient(id).stream().map(this::toMedicalRecordDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(MedicalRecordDto medicalRecordDto) throws SQLException, ClassNotFoundException {
        return medicalRecordsDao.update(toMedicalRecord(medicalRecordDto));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return medicalRecordsDao.delete(id);
    }

    @Override
    public List<MedicalRecordDto> readAll() throws SQLException, ClassNotFoundException {
        return medicalRecordsDao.findAll().stream().map(this::toMedicalRecordDto).collect(Collectors.toList());
    }

    @Override
    public List<MedicalRecordDto> searchMedicalRecords(String searchText) throws Exception {
        try {
            return medicalRecordsDao.searchMedicalRecords(searchText).stream().map(this::toMedicalRecordDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private MedicalRecordDto toMedicalRecordDto(MedicalRecord medicalRecord) {
        return new MedicalRecordDto(
                medicalRecord.getRecord_id(),
                medicalRecord.getPatient_id(),
                medicalRecord.getDoctor_id(),
                medicalRecord.getDiagnosis(),
                medicalRecord.getTreatment(),
                medicalRecord.getRecord_date()
        );
    }

    private MedicalRecord toMedicalRecord(MedicalRecordDto medicalRecordDto) {
        return new MedicalRecord(
                medicalRecordDto.getRecordId(),
                medicalRecordDto.getPatientId(),
                medicalRecordDto.getDoctorId(),
                medicalRecordDto.getDiagnosis(),
                medicalRecordDto.getTreatment(),
                medicalRecordDto.getRecordDate()
        );
    }


}
