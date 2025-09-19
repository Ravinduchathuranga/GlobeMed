package com.rc008code.hms.business.custom.impl;

import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.AdminStaffBo;
import com.rc008code.hms.dto.AdminStaffDto;
import com.rc008code.hms.dto.DoctorDto;
import com.rc008code.hms.entity.AdminStaff;
import com.rc008code.hms.entity.Doctor;
import com.rc008code.hms.repository.DaoFactory;
import com.rc008code.hms.repository.custom.AdminStaffDao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AdminStaffBoImpl implements AdminStaffBo {
    private final AdminStaffDao adminStaffDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.ADMINSTAFF);

    @Override
    public boolean create(AdminStaffDto adminStaffDto) throws SQLException, ClassNotFoundException {
        return adminStaffDao.create(toAdminStaff(adminStaffDto));
    }

    @Override
    public AdminStaffDto find(String id) throws SQLException, ClassNotFoundException {
        AdminStaff adminStaff = adminStaffDao.find(id);
        if (adminStaff != null) {
            return toAdminStaffDto(adminStaff);
        } else {
            return null;
        }
    }

    @Override
    public boolean update(AdminStaffDto adminStaffDto) throws SQLException, ClassNotFoundException {
        return adminStaffDao.update(toAdminStaff(adminStaffDto));
    }

    @Override
    public boolean delete(AdminStaffDto adminStaffDto) throws SQLException, ClassNotFoundException {
        return adminStaffDao.delete(adminStaffDto.getStaff_id());
    }

    @Override
    public List<AdminStaffDto> findAll() throws SQLException, ClassNotFoundException {
        return adminStaffDao.findAll().stream().map(this::toAdminStaffDto).collect(Collectors.toList());
    }

    @Override
    public List<AdminStaff> searchAdminStaff(String searchText) throws Exception {

    }

    private AdminStaffDto toAdminStaffDto(AdminStaff adminStaff) {
        return new AdminStaffDto(
                adminStaff.getStaff_id(),
                adminStaff.getName(),
                adminStaff.getContact_number(),
                adminStaff.getEmail(),
                adminStaff.getPassword()
        );
    }

    private AdminStaff toAdminStaff(AdminStaffDto adminStaffDto) {
        return new AdminStaff(
                adminStaffDto.getStaff_id(),
                adminStaffDto.getName(),
                adminStaffDto.getContact_number(),
                adminStaffDto.getEmail(),
                adminStaffDto.getPassword()
        );
    }
}
