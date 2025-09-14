package com.rc008code.hms.business.custom;

import com.rc008code.hms.dto.AppointmentDto;
import com.rc008code.hms.dto.DoctorDto;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentBo  {
    public boolean create(AppointmentDto entity) throws SQLException, ClassNotFoundException;
    public DoctorDto read(String id) throws SQLException, ClassNotFoundException;
    public boolean update(AppointmentDto entity) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public List<AppointmentDto> readAll() throws SQLException, ClassNotFoundException;
    public List<AppointmentDto> search(String searchText) throws SQLException, ClassNotFoundException;

}
