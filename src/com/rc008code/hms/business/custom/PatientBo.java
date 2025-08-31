package com.rc008code.hms.business.custom;


import com.rc008code.hms.dto.DoctorDto;
import com.rc008code.hms.dto.PatientDto;

import java.sql.SQLException;
import java.util.List;

public interface PatientBo {
    public boolean create(PatientDto entity) throws SQLException, ClassNotFoundException;
    public PatientDto read(String id) throws SQLException, ClassNotFoundException;
    public boolean update(PatientDto entity) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public List<PatientDto> readAll() throws SQLException, ClassNotFoundException;
    public List<PatientDto> search(String searchText) throws SQLException, ClassNotFoundException;
}
