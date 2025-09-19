package com.rc008code.hms.business.custom;

import com.rc008code.hms.dto.AdminStaffDto;
import com.rc008code.hms.entity.AdminStaff;

import java.sql.SQLException;
import java.util.List;

public interface AdminStaffBo {
    public boolean create(AdminStaffDto adminStaffDto) throws SQLException, ClassNotFoundException;

    public AdminStaffDto find(String id) throws SQLException, ClassNotFoundException;

    public boolean update(AdminStaffDto adminStaffDto) throws SQLException, ClassNotFoundException;

    public boolean delete(AdminStaffDto adminStaffDto) throws SQLException, ClassNotFoundException;

    public List<AdminStaffDto> findAll() throws SQLException, ClassNotFoundException;

    List<AdminStaff> searchAdminStaff(String searchText) throws Exception;
}
