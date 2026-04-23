package com.rc008code.hms.repository.custom;

import com.rc008code.hms.entity.Pharmacist;
import com.rc008code.hms.repository.CrudDao;

import java.util.List;

public interface PharmacistDao extends CrudDao<Pharmacist, String> {
    boolean logIn(String email, String password) throws Exception;
    List<Pharmacist> searchPharmacist(String searchText) throws Exception;
}
