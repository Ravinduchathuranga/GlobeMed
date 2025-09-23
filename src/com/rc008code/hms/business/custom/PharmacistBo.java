package com.rc008code.hms.business.custom;


import com.rc008code.hms.dto.PharmacistDto;


import java.sql.SQLException;
import java.util.List;

public interface PharmacistBo {
    public boolean create(PharmacistDto entity) throws SQLException, ClassNotFoundException;
    public PharmacistDto read(String id) throws SQLException, ClassNotFoundException;
    public boolean update(PharmacistDto entity) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public List<PharmacistDto> readAll() throws SQLException, ClassNotFoundException;
    public List<PharmacistDto> search(String searchText) throws SQLException, ClassNotFoundException;
}
