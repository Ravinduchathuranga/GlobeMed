package com.rc008code.hms.business.custom;

import com.rc008code.hms.dto.DoctorDto;

import java.sql.SQLException;
import java.util.List;

public interface DoctorBo {
    public boolean create(DoctorDto entity) throws SQLException, ClassNotFoundException;
    public DoctorDto read(String id) throws SQLException, ClassNotFoundException;
    public boolean update(DoctorDto entity) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public List<DoctorDto> readAll() throws SQLException, ClassNotFoundException;
    public List<DoctorDto> search(String searchText) throws SQLException, ClassNotFoundException;

}
