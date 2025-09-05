package com.rc008code.hms.repository.custom.impl;

import com.rc008code.hms.entity.Pharmacist;
import com.rc008code.hms.repository.custom.PharmacistDao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class PharmacistDaoImpl implements PharmacistDao {
    @Override
    public boolean create(Pharmacist pharmacist) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Pharmacist find(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Pharmacist pharmacist) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Pharmacist> findAll() throws SQLException, ClassNotFoundException {
        return Collections.emptyList();
    }
}
