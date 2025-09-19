package com.rc008code.hms.repository.custom;

import com.rc008code.hms.entity.AdminStaff;
import com.rc008code.hms.repository.CrudDao;

import java.util.List;

public interface AdminStaffDao extends CrudDao<AdminStaff, String> {
    List<AdminStaff> searchAdminStaff(String searchText) throws Exception;
}
