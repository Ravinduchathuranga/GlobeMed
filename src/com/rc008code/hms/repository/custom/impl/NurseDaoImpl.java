package com.rc008code.hms.repository.custom.impl;

import com.rc008code.hms.entity.Nurse;
import com.rc008code.hms.repository.custom.NurseDao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class NurseDaoImpl implements NurseDao {
    @Override
    public boolean create(Nurse nurse) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Nurse find(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Nurse nurse) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Nurse> findAll() throws SQLException, ClassNotFoundException {
        return Collections.emptyList();
    }
}
