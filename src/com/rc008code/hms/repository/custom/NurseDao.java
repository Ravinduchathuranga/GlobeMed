package com.rc008code.hms.repository.custom;

import com.rc008code.hms.entity.Nurse;
import com.rc008code.hms.repository.CrudDao;

import java.util.List;

public interface NurseDao extends CrudDao<Nurse, String> {
    List<Nurse> searchNurse(String searchText) throws Exception;
}
