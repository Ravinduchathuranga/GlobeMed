package com.rc008code.hms.repository.custom.impl;

import com.rc008code.hms.database.MysqlConnection;
import com.rc008code.hms.entity.AdminStaff;
import com.rc008code.hms.repository.CrudUtil;
import com.rc008code.hms.repository.custom.AdminStaffDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    @Override
    public boolean logIn(String username, String password) throws Exception {
        String query = "SELECT * FROM admin_staff WHERE username = '" + username + "'AND password = '" + password + "'";
        System.out.println(query);
        Statement statement = MysqlConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }
}
