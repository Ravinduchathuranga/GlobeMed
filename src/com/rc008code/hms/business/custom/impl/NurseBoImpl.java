package com.rc008code.hms.business.custom.impl;

import com.rc008code.hms.business.custom.NurseBo;
import com.rc008code.hms.dto.NurseDto;
import com.rc008code.hms.entity.Nurse;
import com.rc008code.hms.repository.DaoFactory;
import com.rc008code.hms.repository.custom.NurseDao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NurseBoImpl implements NurseBo {
    private final NurseDao nurseDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.NURSE);

    @Override
    public boolean create(NurseDto entity) throws SQLException, ClassNotFoundException {
        return nurseDao.create(toNurse(entity));
    }

    @Override
    public NurseDto read(String id) throws SQLException, ClassNotFoundException {
        Nurse nurse = nurseDao.find(id);
        if (nurse != null) {
            return toNurseDto(nurse);
        } else {
            return null;
        }
    }

    @Override
    public boolean update(NurseDto entity) throws SQLException, ClassNotFoundException {
        return nurseDao.update(toNurse(entity));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return nurseDao.delete(id);
    }

    @Override
    public List<NurseDto> readAll() throws SQLException, ClassNotFoundException {
        return nurseDao.findAll().stream().map(this::toNurseDto).collect(Collectors.toList());
    }

    @Override
    public List<NurseDto> search(String searchText) throws SQLException, ClassNotFoundException {
        try {
            return nurseDao.searchNurse(searchText).stream().map(this::toNurseDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private NurseDto toNurseDto(Nurse nurse) {
        return new NurseDto(
                nurse.getNurseId(),
                nurse.getName(),
                nurse.getDepartment(),
                nurse.getContact(),
                nurse.getEmail(),
                nurse.getPassword()
        );
    }

    private Nurse toNurse(NurseDto nurseDto) {
        return new Nurse(
                nurseDto.getNurseId(),
                nurseDto.getName(),
                nurseDto.getDepartment(),
                nurseDto.getContact(),
                nurseDto.getEmail(),
                nurseDto.getPassword()
        );
    }
}
