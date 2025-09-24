package com.rc008code.hms.business.custom;

import com.rc008code.hms.dto.PaymentDto;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBo {
    boolean create(PaymentDto entity) throws SQLException, ClassNotFoundException;
    PaymentDto find(String id) throws SQLException, ClassNotFoundException;
    boolean update(PaymentDto entity) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    List<PaymentDto> readAll() throws SQLException, ClassNotFoundException;
}
