package com.rc008code.hms.repository.custom.impl;

import com.rc008code.hms.database.MysqlConnection;
import com.rc008code.hms.entity.Pharmacist;
import com.rc008code.hms.repository.CrudUtil;
import com.rc008code.hms.repository.custom.PharmacistDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PharmacistDaoImpl implements PharmacistDao {
    @Override
    public boolean create(Pharmacist pharmacist) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO pharmacist VALUES (?, ?, ?, ?, ?)",
                pharmacist.getPharmacistId(),
                pharmacist.getName(),
                pharmacist.getContactNumber(),
                pharmacist.getEmail(),
                pharmacist.getPassword()
        );
    }

    @Override
    public Pharmacist find(String entity) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM pharmacist WHERE pharmacist_id=?", entity);
        if (resultSet.next()) {
            return new Pharmacist(
                    resultSet.getString("pharmacist_id"),
                    resultSet.getString("pharmacist_name"),
                    resultSet.getString("contact_no"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
        }
        return null;
    }

    @Override
    public boolean update(Pharmacist pharmacist) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE pharmacist SET pharmacist_name=?, contact_no=?, email=?, password=? WHERE pharmacist_id=?";
        return CrudUtil.execute(sql,
                pharmacist.getName(),
                pharmacist.getContactNumber(),
                pharmacist.getEmail(),
                pharmacist.getPassword(),
                pharmacist.getPharmacistId()
        );
    }

    @Override
    public boolean delete(String entity) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM pharmacist WHERE pharmacist_id=?";
        return CrudUtil.execute(sql, entity);
    }

    @Override
    public List<Pharmacist> findAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM pharmacist");
        List<Pharmacist> pharmacists = new ArrayList<>();
        while (resultSet.next()) {
            pharmacists.add(new Pharmacist(
                    resultSet.getString("pharmacist_id"),
                    resultSet.getString("pharmacist_name"),
                    resultSet.getString("contact_no"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            ));
        }
        return pharmacists;
    }

    @Override
    public boolean logIn(String email, String password) throws Exception {
        String query = "SELECT * FROM pharmacist WHERE email= '" + email + "' AND password = '" + password + "'";
        Statement statement = MysqlConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Pharmacist> searchPharmacist(String searchText) throws Exception {
        searchText = "%" + searchText + "%";
        String query = "SELECT * FROM pharmacist WHERE pharmacist_name LIKE ? OR email LIKE ?";
        ResultSet resultSet = CrudUtil.execute(query, searchText, searchText);
        List<Pharmacist> pharmacists = new ArrayList<>();
        while (resultSet.next()) {
            pharmacists.add(new Pharmacist(
                    resultSet.getString("pharmacist_id"),
                    resultSet.getString("pharmacist_name"),
                    resultSet.getString("contact_no"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            ));
        }
        return pharmacists;
    }
}
