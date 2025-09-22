package com.rc008code.hms.repository.custom;

import com.rc008code.hms.entity.MedicalRecord;
import com.rc008code.hms.repository.CrudDao;

import java.util.List;

public interface MedicalRecordsDao extends CrudDao<MedicalRecord, String> {
    List<MedicalRecord> searchMedicalRecords(String searchText) throws Exception;

}
