package com.rc008code.hms.repository.custom.impl;

import com.rc008code.hms.entity.Nurse;
import com.rc008code.hms.enums.Departments;
import com.rc008code.hms.repository.CrudUtil;
import com.rc008code.hms.repository.custom.NurseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NurseDaoImpl implements NurseDao {
    @Override
    public boolean create(Nurse nurse) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO nurse VALUES (?, ?, ?, ?, ?, ?)",
                nurse.getNurseId(),
                nurse.getName(),
                nurse.getContact(),
                nurse.getDepartment().toString(),
                nurse.getEmail(),
                nurse.getPassword()
        );
    }

    @Override
    public Nurse find(String entity) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM nurse WHERE nurse_id=?", entity);
        if (resultSet.next()) {
            return new Nurse(
                    resultSet.getString("nurse_id"),
                    resultSet.getString("nurse_name"),
                    resultSet.getString("contact_no"),
                    Departments.valueOf(resultSet.getString("department")),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
        }
        return null;
    }

    @Override
    public boolean update(Nurse nurse) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE nurse SET nurse_name=?, contact_no=?, department=?, email=?, password=? WHERE nurse_id=?";
        return CrudUtil.execute(sql,
                nurse.getName(),
                nurse.getContact(),
                nurse.getDepartment().toString(),
                nurse.getEmail(),
                nurse.getPassword(),
                nurse.getNurseId()
        );
    }

    @Override
    public boolean delete(String entity) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM nurse WHERE nurse_id=?";
        return CrudUtil.execute(sql, entity);
    }

    @Override
    public List<Nurse> findAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM nurse");
        List<Nurse> nurses = new ArrayList<>();
        while (resultSet.next()) {
            nurses.add(new Nurse(
                    resultSet.getString("nurse_id"),
                    resultSet.getString("nurse_name"),
                    resultSet.getString("contact_no"),
                    Departments.valueOf(resultSet.getString("department")),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            ));
        }
        return nurses;
    }

    @Override
    public List<Nurse> searchNurse(String searchText) throws Exception {
        searchText = "%" + searchText + "%";
        String query = "SELECT * FROM nurse WHERE nurse_name LIKE ? OR email LIKE ?";
        ResultSet resultSet = CrudUtil.execute(query, searchText, searchText);
        List<Nurse> nurses = new ArrayList<>();
        while (resultSet.next()) {
            nurses.add(new Nurse(
                    resultSet.getString("nurse_id"),
                    resultSet.getString("nurse_name"),
                    resultSet.getString("contact_no"),
                    Departments.valueOf(resultSet.getString("department")),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            ));
        }
        return nurses;
    }
}
