package com.rc008code.hms.repository.custom;

import com.rc008code.hms.entity.Doctor;
import com.rc008code.hms.repository.CrudDao;

import java.util.List;

public interface DoctorDao extends CrudDao<Doctor, String> {
    List<Doctor> searchDoctors(String searchText) throws Exception;
}
