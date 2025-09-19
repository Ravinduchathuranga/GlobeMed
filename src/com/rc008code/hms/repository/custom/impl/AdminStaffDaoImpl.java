package com.rc008code.hms.repository.custom.impl;

import com.rc008code.hms.entity.AdminStaff;
import com.rc008code.hms.repository.custom.AdminStaffDao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class AdminStaffDaoImpl implements AdminStaffDao {
    @Override
    public boolean create(AdminStaff adminStaff) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public AdminStaff find(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(AdminStaff adminStaff) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<AdminStaff> findAll() throws SQLException, ClassNotFoundException {
        return Collections.emptyList();
    }

    @Override
    public List<AdminStaff> searchAdminStaff(String searchText) throws Exception {
        return Collections.emptyList();
    }
}
