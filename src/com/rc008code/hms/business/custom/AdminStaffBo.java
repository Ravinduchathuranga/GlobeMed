package com.rc008code.hms.business.custom;

import com.rc008code.hms.dto.AdminStaffDto;

import java.sql.SQLException;
import java.util.List;

public interface AdminStaffBo {
    public boolean create(AdminStaffDto adminStaffDto) throws SQLException, ClassNotFoundException;

    public AdminStaffDto read(String id) throws SQLException, ClassNotFoundException;

    public boolean update(AdminStaffDto adminStaffDto) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<AdminStaffDto> readAll() throws SQLException, ClassNotFoundException;

    public List<AdminStaffDto> search(String searchText) throws Exception;

    public boolean logIn(String username, String password) throws Exception;
}
