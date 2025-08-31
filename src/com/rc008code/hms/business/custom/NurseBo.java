package com.rc008code.hms.business.custom;


import com.rc008code.hms.dto.NurseDto;


import java.sql.SQLException;
import java.util.List;

public interface NurseBo {
    public boolean create(NurseDto entity) throws SQLException, ClassNotFoundException;
    public NurseDto read(String id) throws SQLException, ClassNotFoundException;
    public boolean update(NurseDto entity) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public List<NurseDto> readAll() throws SQLException, ClassNotFoundException;
}
