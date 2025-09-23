package com.rc008code.hms.business.custom.impl;

import com.rc008code.hms.business.custom.PharmacistBo;
import com.rc008code.hms.dto.PharmacistDto;
import com.rc008code.hms.entity.Pharmacist;
import com.rc008code.hms.repository.DaoFactory;
import com.rc008code.hms.repository.custom.PharmacistDao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PharmacistBoImpl implements PharmacistBo {
    private final PharmacistDao pharmacistDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.PHARMACIST);

    @Override
    public boolean create(PharmacistDto entity) throws SQLException, ClassNotFoundException {
        return pharmacistDao.create(toPharmacist(entity));
    }

    @Override
    public PharmacistDto read(String id) throws SQLException, ClassNotFoundException {
        Pharmacist pharmacist = pharmacistDao.find(id);
        if (pharmacist != null) {
            return toPharmacistDto(pharmacist);
        } else {
            return null;
        }
    }

    @Override
    public boolean update(PharmacistDto entity) throws SQLException, ClassNotFoundException {
        return pharmacistDao.update(toPharmacist(entity));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return pharmacistDao.delete(id);
    }

    @Override
    public List<PharmacistDto> readAll() throws SQLException, ClassNotFoundException {
        return pharmacistDao.findAll().stream().map(this::toPharmacistDto).collect(Collectors.toList());
    }

    @Override
    public List<PharmacistDto> search(String searchText) throws SQLException, ClassNotFoundException {
        try {
            return pharmacistDao.searchPharmacist(searchText).stream().map(this::toPharmacistDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private PharmacistDto toPharmacistDto(Pharmacist pharmacist) {
        return new PharmacistDto(
                pharmacist.getPharmacistId(),
                pharmacist.getName(),
                pharmacist.getContactNumber(),
                pharmacist.getEmail(),
                pharmacist.getPassword()
        );
    }

    private Pharmacist toPharmacist(PharmacistDto pharmacistDto) {
        return new Pharmacist(
                pharmacistDto.getPharmacistId(),
                pharmacistDto.getName(),
                pharmacistDto.getContactNumber(),
                pharmacistDto.getEmail(),
                pharmacistDto.getPassword()
        );
    }
}
